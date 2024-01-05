package com.bellonee.accounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Account microservice REST API Documentation",
				description = "WalletWise Accounts microservice REST API Documentation",
				version = "V1",
				contact = @Contact(
						name = "Wajiu Bello Olarewaju",
						email = "bellowajiuo@gmail.com",
						url = "https://www.wayTech.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.wayTech.com"
				)
		)
)
public class AccountsApplication {

	public static void main(String[] args) {

		SpringApplication.run(AccountsApplication.class, args);
	}

}
