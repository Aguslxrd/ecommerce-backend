package com.damian.ecommerce.backend.application;

import com.damian.ecommerce.backend.domain.model.Category;
import com.damian.ecommerce.backend.domain.port.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryService {

    private final ICategoryRepository iCategoryRepository;

    public CategoryService(ICategoryRepository iCategoryRepository) {
        this.iCategoryRepository = iCategoryRepository;
    }

    public Category save(Category category){
        return iCategoryRepository.save(category);
    }

    public Iterable<Category> findAll(){
        return iCategoryRepository.findAll();
    }

    public Category findById(Integer id){
        return iCategoryRepository.findById(id);
    }

    public void deleteById(Integer id){
        iCategoryRepository.deleteById(id);
    }
}
