package com.osamafarag.moneymanger.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class BrevoEmailService {

    private final WebClient webClient;
    private final String fromEmail;

    public BrevoEmailService(
            @Value("${brevo.api.key}") String apiKey,
            @Value("${brevo.from.email}") String fromEmail
    ) {
        this.fromEmail = fromEmail;

        this.webClient = WebClient.builder()
                .baseUrl("https://api.brevo.com/v3")
                .defaultHeader("api-key", apiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    @Async
    public void sendEmail(String to, String subject, String body) {

        Map<String, Object> request = Map.of(
                "sender", Map.of(
                        "email", fromEmail
                ),
                "to", List.of(
                        Map.of("email", to)
                ),
                "subject", subject,
                "htmlContent", body
        );

        webClient.post()
                .uri("/smtp/email")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                //NOTE: If errors with Emails Change subscibe to block
                .subscribe(
                        response -> System.out.println("Email sent successfully"),
                        error -> System.err.println("Email failed: " + error.getMessage())
                );   //NOTE: if emails get errors, change subscibe to block
    }
}