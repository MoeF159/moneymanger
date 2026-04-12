package com.osamafarag.moneymanger.config;


import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.osamafarag.moneymanger.security.JwtRequestFilter;
import com.osamafarag.moneymanger.service.AppUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @SuppressWarnings("unused")
    private final AppUserDetailsService appUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    @Value("${money.manager.frontend.url}")
    private String frontendUrl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/status","/health","/login","/register","/activate").permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Defines a CORS configuration bean that will be picked up by Spring Security
@Bean
public CorsConfigurationSource corsConfigurationSource() {
    // Create a new CORS configuration object
    CorsConfiguration configuration = new CorsConfiguration();

    // Allow requests only from the specified frontend URL (injected via @Value)
    // Example:
    // - http://localhost:5173 (development)
    // - https://your-frontend.com (production)
    configuration.setAllowedOrigins(List.of("http://localhost:5173", frontendUrl));

    // Specify which HTTP methods are allowed when making cross-origin requests
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

    // Specify which headers can be included in the request
    // "Authorization" is required for JWT tokens
    configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "Accept"));

    // Allow credentials such as cookies or authorization headers to be sent
    // Note: When set to true, "*" cannot be used for allowed origins
    configuration.setAllowCredentials(true);

    // Register the CORS configuration for all endpoints in the application
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    // Return the configured CORS source to be used by Spring Security
    return source;
}

    // @Bean
    // public AuthenticationManager authenticationManager(){
    //     DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    //     authenticationProvider.setUserDetailsService(appUserDetailsService);
    //     authenticationProvider.setPasswordEncoder(passwordEncoder());
    //     return new ProviderManager(authenticationProvider);
    // }

    @Bean
    public AuthenticationManager authenticationManager(org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}