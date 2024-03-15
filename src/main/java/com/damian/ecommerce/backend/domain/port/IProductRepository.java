package com.damian.ecommerce.backend.domain.port;

import com.damian.ecommerce.backend.domain.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository {
    Product save(Product product);
    Iterable<Product> findAll();
    Product findById(Integer id);
    void deleteById(Integer id);
}
