package com.damian.ecommerce.backend.infrastructure.Adapter;

import com.damian.ecommerce.backend.domain.model.OrderState;
import com.damian.ecommerce.backend.infrastructure.Entity.OrderEntity;
import com.damian.ecommerce.backend.infrastructure.Entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface IOrderCrudRepository extends CrudRepository<OrderEntity, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE OrderEntity o SET o.state = :state WHERE o.id = :orderId")
    void updateStateById(Integer orderId, OrderState state);

    Iterable<OrderEntity> findByUserEntity(UserEntity userEntity);
}
