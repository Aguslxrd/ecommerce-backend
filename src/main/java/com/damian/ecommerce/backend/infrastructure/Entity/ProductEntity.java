package com.damian.ecommerce.backend.infrastructure.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String code;
    private String description;
    private String urlImage;
    private BigDecimal price;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dateCreatedAt;
    @UpdateTimestamp
    private LocalDateTime dateUpdatedAt;
    @ManyToOne
    private UserEntity user;
    @ManyToOne
    private CategoryEntity categoryEntity;
}
