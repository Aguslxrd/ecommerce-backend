package com.damian.ecommerce.backend.infrastructure.dto;

public record JwtClientResponse(String token, Integer userIdm, String userType) {
}
