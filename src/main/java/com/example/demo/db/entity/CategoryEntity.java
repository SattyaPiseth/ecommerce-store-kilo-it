package com.example.demo.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
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
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;

    @Column(unique = true,nullable = false)
    private String uuid;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @JoinColumn(name = "created_by")
    @ManyToOne(fetch = FetchType.LAZY)
    @CreatedBy
    private UserEntity createdBy;

    @JoinColumn(name = "updated_by")
    @ManyToOne
    @LastModifiedBy
    private UserEntity updatedBy;

    @Column(name = "created_at")
    @CreatedDate
    private Instant createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Instant updatedAt;

    @OneToMany(mappedBy = "category")
    private Set<ProductEntity> products;

    @ManyToMany(mappedBy = "categories")
    private Set<SupplierEntity> suppliers;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "categories_has_brands",joinColumns =
        @JoinColumn(name = "category_id",referencedColumnName = "id"),
            inverseJoinColumns =
                @JoinColumn(name = "brand_id",referencedColumnName = "id"))
    @JsonProperty("brands")
    private Set<BrandEntity> brands;
}
