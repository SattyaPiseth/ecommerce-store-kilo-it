package com.example.demo.db.repository;

import com.example.demo.db.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sattya
 * create at 1/27/2024 3:50 PM
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
}
