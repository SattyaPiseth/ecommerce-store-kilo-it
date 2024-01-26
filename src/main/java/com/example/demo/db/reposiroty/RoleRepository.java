package com.example.demo.db.reposiroty;

import com.example.demo.db.entity.RoleEntity;
import com.example.demo.model.projection.RoleEntityInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//@RepositoryRestResource(collectionResourceRel = "roles", path = "roles")
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    @Query("select r from RoleEntity r where (:query = 'all' or r.name like concat(:query, '%')) and (:query = 'all' or r.code like concat(:query, '%'))")
    Page<RoleEntityInfo> findByNameStartsWithAndCode(String query, Pageable pageable);

}