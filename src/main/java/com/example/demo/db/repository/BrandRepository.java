package com.example.demo.db.repository;

import com.example.demo.db.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sattya
 * create at 1/28/2024 5:29 PM
 */
public interface BrandRepository extends JpaRepository<BrandEntity,Long> {
}
