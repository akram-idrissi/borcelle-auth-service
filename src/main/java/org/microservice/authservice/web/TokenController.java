package org.microservice.authservice.web;

import org.springframework.http.*;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/token")
public class TokenController {

    private final JwtDecoder jwtDecoder;

    public TokenController(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(RequestHeader("Authorization") String token) {
        try {
            Jwt jwt = jwtDecoder.decode(token);
            return ResponseEntity.ok(Map.of(
                    "valid", true,
                    "subject", jwt.getSubject(),
                    "claims", jwt.getClaims()
            ));
        } catch (JwtException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "valid", false,
                    "error", ex.getMessage()
            ));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

        String clientId = "product-service";
        String clientSecret = "Ce9g2tfePbE1akfr9viAvKBTgxatDkbp";

        String auth = clientId + ":" + clientSecret;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic " + encodedAuth);

        String requestBody = "grant_type=refresh_token&refresh_token=" + refreshToken;

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(
                    "http://localhost:8080/realms/microservice-realm/protocol/openid-connect/token",
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed to refresh token: " + e.getMessage());
        }
    }
}
