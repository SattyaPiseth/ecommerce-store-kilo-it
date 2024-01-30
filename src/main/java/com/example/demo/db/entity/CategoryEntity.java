package com.example.demo.db.entity;

import com.example.demo.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

/**
 * @author Sattya
 * create at 1/27/2024 1:38 PM
 */
@Entity
@Table(name = "categories",indexes = {
        @Index(name = "idx_category_name",columnList = "name",unique = true)
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true,nullable = false)
    private String uuid;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products;

    @ManyToMany(mappedBy = "categories")
    private List<SupplierEntity> suppliers;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "categories_has_brands",joinColumns =
        @JoinColumn(name = "category_id",referencedColumnName = "id"),
            inverseJoinColumns =
                @JoinColumn(name = "brand_id",referencedColumnName = "id"))
    @JsonProperty("brands")
    private List<BrandEntity> brands;

}
