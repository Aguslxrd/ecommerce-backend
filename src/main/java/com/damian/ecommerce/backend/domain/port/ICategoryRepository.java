package com.damian.ecommerce.backend.domain.port;

import com.damian.ecommerce.backend.domain.model.Category;

public interface ICategoryRepository {

    Category save(Category category);
    Iterable<Category> findAll();
    Category findById(Integer id);
    void deleteById();
}
