package com.damian.ecommerce.backend.infrastructure.rest;

import com.damian.ecommerce.backend.infrastructure.dto.JwtClientResponse;
import com.damian.ecommerce.backend.infrastructure.dto.UserDTO;
import com.damian.ecommerce.backend.infrastructure.jwt.JwtGenerator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;

    public LoginController(AuthenticationManager authenticationManager, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtClientResponse> login(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.username(), userDTO.password())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("user role {}", SecurityContextHolder.getContext().
                getAuthentication().
                getAuthorities().
                stream().
                findFirst()
                .get()
                .toString());

        String token = jwtGenerator.getToken(userDTO.username());
        JwtClientResponse jwtClientResponse = new JwtClientResponse(token);

        return new ResponseEntity<>(jwtClientResponse ,HttpStatus.OK);
    }
}