package com.damian.ecommerce.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class User {

    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String cellphone;
    private String password;
    private UserType userType;
    private LocalDateTime dateCreatedAt;
    private LocalDateTime dateUpdatedAt;
}
