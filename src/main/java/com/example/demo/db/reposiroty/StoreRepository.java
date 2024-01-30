package com.example.demo.db.reposiroty;

import com.example.demo.db.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sattya
 * create at 1/28/2024 5:27 PM
 */
public interface StoreRepository extends JpaRepository<StoreEntity,Long> {
}
