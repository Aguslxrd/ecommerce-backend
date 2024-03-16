package com.damian.ecommerce.backend.infrastructure.rest;

import com.damian.ecommerce.backend.application.OrderService;
import com.damian.ecommerce.backend.domain.model.Order;
import com.damian.ecommerce.backend.domain.model.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order){
        if (order.getOrderState().toString().equals(OrderState.CANCELLED.toString())){
            order.setOrderState(OrderState.CANCELLED);
        }else{
            order.setOrderState(OrderState.CONFIRMED);
        }
        return ResponseEntity.ok(orderService.save(order));
    }

    @PostMapping("/update/state/order")
    public ResponseEntity updateStateById(@RequestParam Integer orderId, @RequestParam String state){
        orderService.updateStateById(orderId,state);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Order>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }
}