package com.damian.ecommerce.backend.infrastructure.Adapter;

import com.damian.ecommerce.backend.infrastructure.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface IUserCrudRepository extends CrudRepository<UserEntity, Integer> {
}
