package com.example.demo.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

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
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String uuid;

    @Size(max = 30)
    @Column(name = "code",nullable = false,unique = true)
    private String code;

    @Size(max = 150)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url")
    private String image;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

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

    @ManyToOne
    @JoinColumn(name = "cate_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;

    @OneToMany(mappedBy = "product")
    private Set<InventoryEntity> inventories;
}
