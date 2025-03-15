package com.ervingorospe.grab_api_gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
    @GetMapping("/auth")
    public ResponseEntity<String> authServiceFallback() {
        return ResponseEntity.status(503).body("Auth Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/user")
    public ResponseEntity<String> userServiceFallback() {
        return ResponseEntity.status(503).body("User Service is currently unavailable. Please try again later.");
    }
}
