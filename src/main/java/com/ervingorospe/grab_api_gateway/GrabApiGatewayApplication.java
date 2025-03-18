package com.ervingorospe.grab_api_gateway;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrabApiGatewayApplication {

	public static void main(String[] args) {
		// Load .env file
		String isKubernetes = System.getenv("KUBERNETES_SERVICE_HOST");

		if (isKubernetes == null) {
			// Running locally (IntelliJ)
			Dotenv dotenv = Dotenv.configure().load();
			System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
			System.setProperty("AUTH_SERVICE_URL", dotenv.get("AUTH_SERVICE_URL"));
			System.setProperty("USER_SERVICE_URL", dotenv.get("USER_SERVICE_URL"));
			System.setProperty("TOKEN_SERVICE_URL", dotenv.get("TOKEN_SERVICE_URL"));
		} else {
			// Running in Kubernetes (use environment variables)
			System.setProperty("JWT_SECRET", System.getenv("JWT_SECRET"));
			System.setProperty("AUTH_SERVICE_URL", System.getenv("AUTH_SERVICE_URL"));
			System.setProperty("USER_SERVICE_URL", System.getenv("USER_SERVICE_URL"));
			System.setProperty("TOKEN_SERVICE_URL", System.getenv("TOKEN_SERVICE_URL"));
		}

		SpringApplication.run(GrabApiGatewayApplication.class, args);
	}
}
