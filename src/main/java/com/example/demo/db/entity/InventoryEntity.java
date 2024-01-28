package com.example.demo.db.entity;

import com.example.demo.db.status.InventoryStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Sattya
 * create at 1/28/2024 4:26 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventories",indexes = {
        @Index(name = "idx_unit_cost",columnList = "unit_cost"),
        @Index(name = "idx_quantity",columnList = "quantity"),
        @Index(name = "idx_location",columnList = "location"),
        @Index(name = "idx_last_restock_date",columnList = "last_restock_date"),
        @Index(name = "idx_status",columnList = "status")
})
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer id;

    @Column(name = "unit_cost",nullable = false,precision = 10,scale = 2)
    private BigDecimal unit_cost;

    @Column(name = "quantity",nullable = false)
    private Integer quantity;

    @Size(max = 200)
    @Column(name = "location",nullable = false)
    private String location;

    @Column(name = "last_restock_date",nullable = false)
    private LocalDate last_restock_date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private InventoryStatus status;

    @ManyToOne
    @JoinColumn(name = "pro_id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreEntity store;
}
