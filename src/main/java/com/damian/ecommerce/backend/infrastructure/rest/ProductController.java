package com.damian.ecommerce.backend.infrastructure.rest;

import com.damian.ecommerce.backend.application.ProductService;
import com.damian.ecommerce.backend.domain.model.Category;
import com.damian.ecommerce.backend.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/products")
@Slf4j
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product){
        if (product == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Nombre producto: {}" , product.getName());
        log.info("User ID: {}" , product.getUserId());
        log.info("Category ID: {}" , product.getCategoryId());
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id) {
        Product product = productService.findById(id);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id){
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
