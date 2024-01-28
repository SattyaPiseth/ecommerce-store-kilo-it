package com.example.demo.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * @author Sattya
 * create at 1/28/2024 3:33 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "suppliers",indexes = {
        @Index(name = "idx_supplier_company_name",columnList = "company_name",unique = true),
        @Index(name = "idx_supplier_contact_email",columnList = "contact_email",unique = true),
        @Index(name = "idx_supplier_contact_phone",columnList = "contact_phone",unique = true)
})
public class SupplierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true,nullable = false)
    private String uuid;

    @Size(max = 150)
    @Column(name = "company_name",nullable = false,unique = true)
    private String companyName;

    @Column(nullable = false,length = 200)
    private String address;

    @Size(max = 100)
    @Column(name = "city",nullable = false)
    private String city;

    @Size(max = 50)
    @Column(name = "country",nullable = false)
    private String country;

    @Size(max = 100)
    @Column(name = "contact_email",nullable = false,unique = true)
    private String email;

    @Size(max = 50)
    @Column(name = "contact_phone",nullable = false)
    private String phone;

    @OneToMany(mappedBy = "supplier")
    private Set<ProductEntity> products;

    @OneToMany(mappedBy = "supplier")
    private Set<InventoryEntity> inventories;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "suppliers_has_categories",joinColumns =
        @JoinColumn(name = "supplier_id",referencedColumnName = "id"),
            inverseJoinColumns =
                @JoinColumn(name = "category_id",referencedColumnName = "id"))
    @JsonProperty("categories")
    private Set<CategoryEntity> categories;

}
