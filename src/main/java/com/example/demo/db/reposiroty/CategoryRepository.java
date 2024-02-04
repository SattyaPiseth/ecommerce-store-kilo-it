package com.example.demo.db.reposiroty;

import com.example.demo.db.entity.CategoryEntity;
import com.example.demo.model.projection.CategoryEntityInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author Sattya
 * create at 1/27/2024 3:50 PM
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {
//    @Query("select c from CategoryEntity c where (:query = 'all' or c.name like concat(:query, '%'))")
//    Page<CategoryEntityInfo> findByNameContains(@Param("query") String query, Pageable pageable);

    @Query("select c from CategoryEntity c where (:query = 'all' or c.name like concat(:query, '%')) and c.isDeleted = false")
    Page<CategoryEntityInfo> findByNameContainsAndIsDeletedFalse(@Param("query") String query, Pageable pageable);

    @Query("select c from CategoryEntity c where c.name = :name and c.isDeleted = true")
    CategoryEntity findByNameAndIsDeletedTrue(@Param("name") String name);


    @Query("select (count(c) > 0) from CategoryEntity c where c.name = :name and c.isDeleted = false")
    boolean existsByNameAndIsDeletedFalse(@Param("name") String name);


  Optional<CategoryEntity> findByUuid(String uuid);



}
