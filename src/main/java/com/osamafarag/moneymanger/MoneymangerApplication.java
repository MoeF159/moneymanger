package com.osamafarag.moneymanger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAsync
@SpringBootApplication
public class MoneymangerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneymangerApplication.class, args);
	}

}
