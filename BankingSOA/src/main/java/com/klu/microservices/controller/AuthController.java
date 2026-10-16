
package com.klu.microservices.controller;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.klu.microservices.dto.LoginRequest;
import com.klu.microservices.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @RequestBody LoginRequest request) {

        if (request.getUsername() == null ||
            request.getPassword() == null ||
            request.getUsername().isBlank() ||
            request.getPassword().isBlank()) {

            return ResponseEntity.badRequest().body(
                Map.of("message", "Username and password are required\n Unauthorized"));
        }

        if (authService.validateCredentials(
                request.getUsername(), request.getPassword())) {

            return ResponseEntity.ok(
                Map.of("message", "Login successful\n Authorized"));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
            Map.of("message", "Invalid username or password\n Unauthorized"));
    }
}
