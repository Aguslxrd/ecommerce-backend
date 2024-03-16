package com.damian.ecommerce.backend.application;

import com.damian.ecommerce.backend.domain.model.Order;
import com.damian.ecommerce.backend.domain.port.IOrderRepository;

public class OrderService {
    private final IOrderRepository iOrderRepository;

    public OrderService(IOrderRepository iOrderRepository) {
        this.iOrderRepository = iOrderRepository;
    }

    public Order save(Order order){
        return iOrderRepository.save(order);
    }

    public Iterable<Order> findAll(){
        return  iOrderRepository.findAll();
    }

    public Iterable<Order> findByUserId(Integer id){
        return iOrderRepository.findByUserId(id);
    }

    public void updateStateById(Integer orderId, String state){
        iOrderRepository.updateStateById(orderId, state);
    }
}
