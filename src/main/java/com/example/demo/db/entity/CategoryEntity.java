package com.example.demo.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;
import java.util.Set;

/**
 * @author Sattya
 * create at 1/27/2024 1:38 PM
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
//@SQLDelete(sql = "update categories set deleted =true where id=?")
//@Where(clause = "deleted=false")
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
    @Column(name = "deleted",columnDefinition = "boolean default false")
    private Boolean isDeleted;
    private Instant delete_at;

}
