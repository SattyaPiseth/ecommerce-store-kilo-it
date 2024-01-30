package com.example.demo.db.reposiroty;

import com.example.demo.db.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryEntity,Long> {
}
