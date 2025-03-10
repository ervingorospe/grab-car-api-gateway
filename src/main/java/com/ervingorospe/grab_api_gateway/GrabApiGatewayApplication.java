package com.ervingorospe.grab_api_gateway;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrabApiGatewayApplication {

	public static void main(String[] args) {
		// Load .env file
//		Dotenv dotenv = Dotenv.configure().load();
//		System.setProperty("DB_URL", System.getenv("DB_URL"));
//		System.setProperty("DB_USERNAME", System.getenv("DB_USERNAME"));
//		System.setProperty("DB_PASSWORD", System.getenv("DB_PASSWORD"));
//		System.setProperty("DB_DRIVER", System.getenv("DB_DRIVER"));
//		System.setProperty("JWT_SECRET", System.getenv("JWT_SECRET"));
//		System.setProperty("JWT_expiration", System.getenv("JWT_expiration"));
//		System.setProperty("JWT_REFRESH_EXPIRATION", System.getenv("JWT_REFRESH_EXPIRATION"));
//		System.setProperty("PORT", System.getenv("PORT_API_GATEWAY"));

		SpringApplication.run(GrabApiGatewayApplication.class, args);
	}

}
