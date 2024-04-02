package com.damian.ecommerce.backend.infrastructure.jwt;

import com.damian.ecommerce.backend.infrastructure.Service.CustomUserDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static com.damian.ecommerce.backend.infrastructure.jwt.Constants.*;

public class JwtValidation {
    public static boolean tokenExistsOnRequest(HttpServletRequest request, HttpServletResponse httpServletResponse){
        String header = request.getHeader(HEADER_AUTHORIZATION);
        if (header == null || !header.startsWith(TOKEN_BEARER_PREFIX)){
            return false;
        }
        return true;
    }

    public static Claims JWTIsValid(HttpServletRequest request){
        String jwtToken = request.getHeader(HEADER_AUTHORIZATION).replace(TOKEN_BEARER_PREFIX, "");
        return Jwts.parserBuilder()
                .setSigningKey(getSignedKey(SECRET_KEY))
                .build().parseClaimsJwt(jwtToken).getBody();
    }

    //autentica al usuario credenciales y role
    public static void setAuthentication(Claims claims, CustomUserDetailService customUserDetailService){
        UserDetails userDetails = customUserDetailService.loadUserByUsername(claims.getSubject()); // trae el username = email de la persona autenticada
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
