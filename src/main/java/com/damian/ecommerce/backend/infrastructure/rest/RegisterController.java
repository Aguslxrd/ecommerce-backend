package com.damian.ecommerce.backend.infrastructure.rest;

import com.damian.ecommerce.backend.application.RegistrationService;
import com.damian.ecommerce.backend.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class RegisterController {
    private final RegistrationService registrationService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegisterController(RegistrationService registrationService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.registrationService = registrationService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        if (user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return new ResponseEntity<>(registrationService.register(user), HttpStatus.CREATED);
    }

}
