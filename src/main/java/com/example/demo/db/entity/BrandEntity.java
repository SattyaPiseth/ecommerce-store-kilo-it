package com.example.demo.db.entity;

import com.example.demo.base.BaseEntity;
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

import java.time.Instant;
import java.util.List;
import java.util.Set;

/**
 * @author Sattya
 * create at 1/28/2024 3:21 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "brands",indexes = {
        @Index(name = "idx_brand_name",columnList = "brand_name",unique = true)
})
public class BrandEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true,nullable = false)
    private String uuid;
    @Size(max = 100)
    @Column(name = "brand_name",nullable = false)
    private String name;

    @ManyToMany(mappedBy = "brands")
    private List<CategoryEntity> categories;

    @OneToMany(mappedBy = "brand")
    private List<ProductEntity> products;

}
