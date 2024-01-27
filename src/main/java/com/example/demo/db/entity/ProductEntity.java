package com.example.demo.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Sattya
 * create at 1/27/2024 1:47 PM
 */
@Entity
@Table(name = "products",indexes = {
        @Index(name = "idx_product_uuid",columnList = "uuid",unique = true),
        @Index(name = "idx_product_code",columnList = "code",unique = true),
        @Index(name = "idx_product_name",columnList = "name",unique = true)
})
@Getter
@Setter
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String uuid;

    @Size(max = 30)
    @Column(name = "pro_code",nullable = false,unique = true)
    private String code;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url")
    private String image;

    @Column(name = "unit_price")
    private Double unitPrice;

    @ManyToOne
    @JoinColumn(name = "cate_id")
    private CategoryEntity category;
}
