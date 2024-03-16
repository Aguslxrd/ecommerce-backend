package com.damian.ecommerce.backend.infrastructure.Exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Integer id) {
        super("Cateogoria con id " + id + " no encontrado");
    }
}