package com.example.demo.db.reposiroty;

import com.example.demo.db.entity.CategoryEntity;
import com.example.demo.model.projection.CategoryEntityInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Sattya
 * create at 1/27/2024 3:50 PM
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {
    @Query("select c from CategoryEntity c where (:query = 'all' or c.name like concat(:query, '%'))")
    Page<CategoryEntityInfo> findByNameContains(@Param("query") String query, Pageable pageable);



//    @Query("select c from CategoryEntity c where (:query = 'all' or c.uuid like concat(:query, '%')) and (:query = 'all' or c.name like concat(:query, '%'))")
//    Page<CategoryEntity> findByUuidStartsWithOrName(String uuid, String name, Pageable pageable);


    @Query("select (count(c) > 0) from CategoryEntity c where c.name like concat(:name, '%')")
    boolean existsByNameStartsWith(@Param("name") String name);
}
