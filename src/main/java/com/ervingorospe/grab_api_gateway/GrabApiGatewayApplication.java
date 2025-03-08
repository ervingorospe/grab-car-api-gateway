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
			System.setProperty("PORT", dotenv.get("PORT_API_GATEWAY"));
		} else {
			// Running in Kubernetes (use environment variables)
			System.setProperty("JWT_SECRET", System.getenv("JWT_SECRET"));
			System.setProperty("PORT", System.getenv("PORT_API_GATEWAY"));
		}

		SpringApplication.run(GrabApiGatewayApplication.class, args);
	}

}
