package com.damian.ecommerce.backend.infrastructure.Adapter;

import com.damian.ecommerce.backend.infrastructure.Entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface IProductCrudRepository extends CrudRepository<ProductEntity, Integer> {
}
