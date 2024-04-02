package com.damian.ecommerce.backend.infrastructure.Adapter;

import com.damian.ecommerce.backend.domain.model.User;
import com.damian.ecommerce.backend.domain.port.IUserRepository;
import com.damian.ecommerce.backend.infrastructure.Mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserCrudRepositoryImpl implements IUserRepository {

    private final IUserCrudRepository iUserCrudRepository;

    private final UserMapper userMapper;

    public UserCrudRepositoryImpl(IUserCrudRepository iUserCrudRepository, UserMapper userMapper) {
        this.iUserCrudRepository = iUserCrudRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        return userMapper.toUser(iUserCrudRepository.save(userMapper.toUserEntity(user)));
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.toUser(iUserCrudRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not found!")));
    }

    @Override
    public User findById(Integer id) {
        return iUserCrudRepository.findById(id)
                .map(userMapper::toUser)
                .orElse(null);
    }
}
