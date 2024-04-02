package com.damian.ecommerce.backend.infrastructure.rest;

import com.damian.ecommerce.backend.application.RegistrationService;
import com.damian.ecommerce.backend.domain.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class RegisterController {
    private final RegistrationService registrationService;

    public RegisterController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        if (user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(registrationService.register(user), HttpStatus.CREATED);
    }

}
