package com.damian.ecommerce.backend.infrastructure.config;

import com.damian.ecommerce.backend.application.UserService;
import com.damian.ecommerce.backend.domain.port.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public UserService userService(IUserRepository iUserRepository){
        return new UserService(iUserRepository);
    }
}
