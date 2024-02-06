package com.example.demo.db.repository;

import com.example.demo.db.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sattya
 * create at 1/27/2024 3:52 PM
 */
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
}
