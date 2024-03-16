package com.damian.ecommerce.backend.infrastructure.Adapter;

import com.damian.ecommerce.backend.domain.model.Order;
import com.damian.ecommerce.backend.domain.model.OrderState;
import com.damian.ecommerce.backend.domain.port.IOrderRepository;
import com.damian.ecommerce.backend.infrastructure.Entity.OrderEntity;
import com.damian.ecommerce.backend.infrastructure.Entity.UserEntity;
import com.damian.ecommerce.backend.infrastructure.Exceptions.OrderNotFoundException;
import com.damian.ecommerce.backend.infrastructure.Mapper.IOrderMapper;
import org.springframework.stereotype.Repository;

@Repository
public class OrderCrudRepositoryImpl implements IOrderRepository {
    private final IOrderMapper iOrderMapper;
    private final IOrderCrudRepository iOrderCrudRepository;

    public OrderCrudRepositoryImpl(IOrderMapper iOrderMapper, IOrderCrudRepository iOrderCrudRepository) {
        this.iOrderMapper = iOrderMapper;
        this.iOrderCrudRepository = iOrderCrudRepository;
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = iOrderMapper.toOrderEntity(order);
        orderEntity.getOrderProducts().forEach(
                orderProductEntity -> orderProductEntity.setOrderEntity(orderEntity)
        );
        return iOrderMapper.toOrder(iOrderCrudRepository.save(orderEntity));
    }

    @Override
    public Order findById(Integer id) {
        return iOrderMapper.toOrder(iOrderCrudRepository.findById(id).orElseThrow(
                () -> new OrderNotFoundException(id)
        ));
    }

    @Override
    public Iterable<Order> findAll() {
        return iOrderMapper.toOrderList(iOrderCrudRepository.findAll());
    }

    @Override
    public Iterable<Order> findByUserId(Integer userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        return iOrderMapper.toOrderList(iOrderCrudRepository.findByUserEntity(userEntity));
    }

    @Override
    public void updateStateById(Integer orderId, String state) {
        if (state.equals(OrderState.CANCELLED)){
            iOrderCrudRepository.updateStateById(orderId, OrderState.CANCELLED);
        }else {
            iOrderCrudRepository.updateStateById(orderId, OrderState.CONFIRMED);
        }

    }
}
