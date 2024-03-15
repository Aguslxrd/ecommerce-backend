package com.damian.ecommerce.backend.infrastructure.Adapter;

import com.damian.ecommerce.backend.infrastructure.Entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryCrudRepository extends CrudRepository<CategoryEntity, Integer> {
}
