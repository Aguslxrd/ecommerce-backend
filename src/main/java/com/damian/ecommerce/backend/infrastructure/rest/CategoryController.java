package com.damian.ecommerce.backend.infrastructure.rest;

import com.damian.ecommerce.backend.application.CategoryService;
import com.damian.ecommerce.backend.domain.model.Category;
import com.damian.ecommerce.backend.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/categories")
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category save(@RequestBody Category category){
        return categoryService.save(category);
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable Integer id){
        return  categoryService.findById(id);
    }

    @GetMapping
    public Iterable<Category> findAll(){return categoryService.findAll();}

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        categoryService.deleteById(id);
    }

}
