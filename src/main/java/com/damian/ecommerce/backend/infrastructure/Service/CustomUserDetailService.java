package com.damian.ecommerce.backend.infrastructure.Service;

import com.damian.ecommerce.backend.application.UserService;
import com.damian.ecommerce.backend.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username);
        log.info("loadsByUsername {}", username);
        return org.springframework.security.core.userdetails.User.builder().username(user.getEmail())
                .password(user.getPassword())
                .roles(String.valueOf(user.getUserType()))
                .build();
    }
}
