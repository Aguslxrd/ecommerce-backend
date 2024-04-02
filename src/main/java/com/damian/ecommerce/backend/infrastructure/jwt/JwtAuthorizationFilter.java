package com.damian.ecommerce.backend.infrastructure.jwt;

import com.damian.ecommerce.backend.infrastructure.Service.CustomUserDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.damian.ecommerce.backend.infrastructure.jwt.JwtValidation.*;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private CustomUserDetailService customUserDetailService;

    public JwtAuthorizationFilter(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (tokenExistsOnRequest(request, response)) {
                Claims claims = JWTIsValid(request);
                if (claims.get("authorities") != null) {
                    setAuthentication(claims, customUserDetailService);
                } else {
                    SecurityContextHolder.clearContext(); // se elimina si el token es null
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(request, response);
        }catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException exception){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;

        }

    }

}
