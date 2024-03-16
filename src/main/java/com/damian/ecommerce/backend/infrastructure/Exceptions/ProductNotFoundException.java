package com.damian.ecommerce.backend.infrastructure.Exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Integer id) {
        super("Producto con id " + id + " no encontrado");
    }

}