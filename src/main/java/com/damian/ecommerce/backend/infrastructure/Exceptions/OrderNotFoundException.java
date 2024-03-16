package com.damian.ecommerce.backend.infrastructure.Exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Integer id) {
        super("Orden con id " + id + " no encontrada");
    }
}
