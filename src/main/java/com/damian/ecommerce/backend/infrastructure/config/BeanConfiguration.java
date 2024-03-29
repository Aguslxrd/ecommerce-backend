package com.damian.ecommerce.backend.infrastructure.config;

import com.damian.ecommerce.backend.application.*;
import com.damian.ecommerce.backend.domain.port.ICategoryRepository;
import com.damian.ecommerce.backend.domain.port.IOrderRepository;
import com.damian.ecommerce.backend.domain.port.IProductRepository;
import com.damian.ecommerce.backend.domain.port.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public UserService userService(IUserRepository iUserRepository){

        return new UserService(iUserRepository);
    }

    @Bean
    public CategoryService categoryService(ICategoryRepository iCategoryRepository){
        return new CategoryService(iCategoryRepository);
    }

    @Bean
    public ProductService productService(IProductRepository iProductRepository, UploadFileService uploadFileService){
        return new ProductService(iProductRepository, uploadFileService);
    }

    @Bean
    public OrderService orderService(IOrderRepository iOrderRepository){
        return new OrderService(iOrderRepository);
    }
}
