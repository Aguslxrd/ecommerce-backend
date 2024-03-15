package com.damian.ecommerce.backend.infrastructure.Adapter;

import com.damian.ecommerce.backend.domain.model.User;
import com.damian.ecommerce.backend.domain.port.IUserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserCrudRepositoryImpl implements IUserRepository {

    private final IUserCrudRepository iUserCrudRepository;

    public UserCrudRepositoryImpl(IUserCrudRepository iUserCrudRepository) {
        this.iUserCrudRepository = iUserCrudRepository;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }
}
