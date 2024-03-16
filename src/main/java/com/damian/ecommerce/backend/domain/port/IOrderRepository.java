package com.damian.ecommerce.backend.domain.port;

import com.damian.ecommerce.backend.domain.model.Order;

public interface IOrderRepository {
    Order save (Order order);
    Order findById (Integer id);
    Iterable<Order> findAll();
    Iterable<Order> findByUserId(Integer userId);
    void updateStateById(Integer orderId, String state);
}
