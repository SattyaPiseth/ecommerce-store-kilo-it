package com.example.demo.db.reposiroty;

import com.example.demo.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("select u from UserEntity u join fetch u.roleEntity role left join fetch role.permissionEntities where u.username = :username")
    UserEntity findByUsernameFetchRolePermission(@Param("username") String username);

    @Query("""
            select (count(u) > 0) from UserEntity u
            where u.username = :username or u.email = :email and u.isDeleted = false""")
    boolean existsByUsernameOrEmailAndIsDeletedFalse(@Param("username") String username, @Param("email") String email);


    @Query("select u from UserEntity u where u.username = :username and u.isDeleted = false and u.status = true")
    Optional<UserEntity> findByUsernameAndIsDeletedFalseAndStatusTrue(@Param("username") String username);
}