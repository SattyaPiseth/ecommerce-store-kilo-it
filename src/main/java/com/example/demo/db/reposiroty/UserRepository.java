package com.example.demo.db.reposiroty;

import com.example.demo.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("select u from UserEntity u join fetch u.roleEntity role left join fetch role.permissionEntities where u.username = :username")
    UserEntity findByUsernameFetchRolePermission(@Param("username") String username);

}