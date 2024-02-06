package com.example.demo.db.repository;

import com.example.demo.db.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sattya
 * create at 2/6/2024 12:28 PM
 */
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
}
