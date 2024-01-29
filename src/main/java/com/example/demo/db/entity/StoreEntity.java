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

import java.time.Instant;
import java.util.Set;

/**
 * @author Sattya
 * create at 1/28/2024 4:37 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stores",indexes = {
        @Index(name = "idx_store_name",columnList = "store_name",unique = true),
        @Index(name = "idx_phone_number",columnList = "phone_number",unique = true),
        @Index(name = "idx_email",columnList = "email",unique = true)
})
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true,nullable = false)
    private String uuid;

    @Size(max = 50)
    @Column(name = "store_name",unique = true,nullable = false)
    private String storeName;

    @Size(max = 50)
    @Column(name = "phone_number",nullable = false,unique = true)
    private String phoneNumber;

    @Size(max = 100)
    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(nullable = false,length = 200)
    private String address;

    @Size(max = 100)
    @Column(name = "city",nullable = false)
    private String city;

    @Size(max = 50)
    @Column(name = "country",nullable = false)
    private String country;

    @Size(max = 10)
    @Column(name = "zip_code")
    private String zipCode;

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

    @OneToMany(mappedBy = "store")
    private Set<InventoryEntity> inventories;

}
