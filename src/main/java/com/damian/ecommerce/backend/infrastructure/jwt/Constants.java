package com.damian.ecommerce.backend.infrastructure.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public class Constants {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";
    public static String SECRET_KEY;

    @Value("${jwt_secret_key}") //secret key proveniente de app.properties
    public void setSecretKey(String secretKey) {
        SECRET_KEY = secretKey;
    }
    public static final long TOKEN_EXPIRATION_TIME = 3000000; //30 minutos

    public static Key getSignedKey(String secretKey){
        byte [] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
