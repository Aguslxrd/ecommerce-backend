package com.damian.ecommerce.backend.infrastructure.Mapper;

import com.damian.ecommerce.backend.domain.model.Category;
import com.damian.ecommerce.backend.domain.model.User;
import com.damian.ecommerce.backend.infrastructure.Entity.CategoryEntity;
import com.damian.ecommerce.backend.infrastructure.Entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "dateCreatedAt", target = "dateCreatedAt"),
            @Mapping(source = "dateUpdatedAt", target = "dateUpdatedAt")
    })
    Category toCategoryEntity(CategoryEntity categoryEntity);

    Iterable<Category> toCategoryList(Iterable<CategoryEntity> categoryEntities);

    @InheritInverseConfiguration
    CategoryEntity toCategoryEntity(Category category);
}
