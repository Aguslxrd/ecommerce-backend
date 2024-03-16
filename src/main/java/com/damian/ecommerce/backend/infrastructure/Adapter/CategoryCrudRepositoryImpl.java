package com.damian.ecommerce.backend.infrastructure.Adapter;

import com.damian.ecommerce.backend.domain.model.Category;
import com.damian.ecommerce.backend.domain.port.ICategoryRepository;
import com.damian.ecommerce.backend.infrastructure.Exceptions.CategoryNotFoundException;
import com.damian.ecommerce.backend.infrastructure.Mapper.CategoryMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryCrudRepositoryImpl implements ICategoryRepository {

    private final ICategoryCrudRepository iCategoryCrudRepository;
    private final CategoryMapper categoryMapper;

    public CategoryCrudRepositoryImpl(ICategoryCrudRepository iCategoryCrudRepository, CategoryMapper categoryMapper) {
        this.iCategoryCrudRepository = iCategoryCrudRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Category save(Category category) {
        return categoryMapper.toCategory(iCategoryCrudRepository.save(categoryMapper.toCategoryEntity(category)));
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryMapper.toCategoryList(iCategoryCrudRepository.findAll());
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.toCategory(iCategoryCrudRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException(id)
        ));
    }

    @Override
    public void deleteById(Integer id) {
        iCategoryCrudRepository.deleteById(id);
    }
}
