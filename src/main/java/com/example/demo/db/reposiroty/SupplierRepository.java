package com.example.demo.db.reposiroty;

import com.example.demo.db.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sattya
 * create at 1/28/2024 5:26 PM
 */
public interface SupplierRepository extends JpaRepository<SupplierEntity,Integer> {
}
