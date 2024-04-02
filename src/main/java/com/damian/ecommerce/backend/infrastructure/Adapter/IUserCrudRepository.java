package com.damian.ecommerce.backend.infrastructure.Adapter;

import com.damian.ecommerce.backend.infrastructure.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserCrudRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
}
