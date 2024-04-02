package com.damian.ecommerce.backend.application;

import com.damian.ecommerce.backend.domain.model.User;
import com.damian.ecommerce.backend.domain.port.IUserRepository;

public class RegistrationService {
    private final IUserRepository iUserRepository;

    public RegistrationService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public User register(User user){
        return iUserRepository.save(user);
    }
}
