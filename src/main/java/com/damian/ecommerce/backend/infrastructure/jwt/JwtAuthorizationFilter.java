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
            if (tokenExists(request, response)) {
                Claims claims = JWTValid(request);
                if (claims.get("authorities") != null) {
                    setAuthetication(claims, customUserDetailService);
                } else {
                    clearSecurityContext();
                }
            } else {
                clearSecurityContext();
            }
            filterChain.doFilter(request, response);
        }catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException exception){
            handleJwtException(response, exception);
            return;

        }

    }

    private void handleJwtException(HttpServletResponse response, Exception exception) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.sendError(HttpServletResponse.SC_FORBIDDEN, exception.getMessage());
    }

    private void clearSecurityContext() { // se elimina si el token es null
        SecurityContextHolder.clearContext();
    }

}
