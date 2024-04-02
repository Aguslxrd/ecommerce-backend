package com.damian.ecommerce.backend.infrastructure.config;

import com.damian.ecommerce.backend.infrastructure.jwt.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final JwtAuthorizationFilter authorizationFilter;

    public SecurityConfig(JwtAuthorizationFilter authorizationFilter) {
        this.authorizationFilter = authorizationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable()).authorizeRequests(
                auth -> auth.requestMatchers("/api/v1/admin/categories/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/admin/products/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/orders/**").hasRole("USER")
                        .requestMatchers("/api/v1/payments/**").hasRole("USER")
                        .requestMatchers("/api/v1/public/home/products").permitAll()
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/api/v1/auth/**").permitAll().anyRequest().authenticated()
        ).addFilterAfter(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
