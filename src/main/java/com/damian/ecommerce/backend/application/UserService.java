package com.damian.ecommerce.backend.application;

import com.damian.ecommerce.backend.domain.model.User;
import com.damian.ecommerce.backend.domain.port.IUserRepository;

public class UserService {

    private final IUserRepository iUserRepository;

    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public User save(User user){
        return iUserRepository.save(user);
    }

    public User findById(Integer id){
        return iUserRepository.findById(id);
    }

    public User findByEmail(String email){
        return iUserRepository.findByEmail(email);
    }
}
