package com.example.demo.db.reposiroty;

import com.example.demo.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author Sattya
 * create at 1/29/2024 11:10 PM
 */
public interface AuthRepository extends JpaRepository<UserEntity,Long> {
    @Modifying
    @Query("UPDATE UserEntity AS u SET u.verifiedCode = :verifiedCode WHERE u.username = :username")
    void updateVerifiedCode(@Param("username") String username,
                            @Param("verifiedCode") String verifiedCode);
    @Modifying
    @Query("UPDATE UserEntity AS u SET u.verifiedToken = :verifiedToken WHERE u.username = :username")
    void updateIsVerifiedToken(@Param("username") String username,@Param("verifiedToken") String verifiedToken);

    @Query("select u from UserEntity u where u.email = :email and u.verifiedCode = :verifiedCode and u.isDeleted = false")
    Optional<UserEntity> findByEmailAndVerifiedCodeAndIsDeletedFalse(@Param("email") String email, @Param("verifiedCode") String verifiedCode);


    @Query("select u from UserEntity u where u.verifiedToken = :verifiedToken and u.isDeleted = false")
    Optional<UserEntity> findByVerifiedTokenAndIsDeletedFalse(@Param("verifiedToken") String verifiedToken);
    @Query("""
        select u from UserEntity u
        where u.verifiedToken = :verifiedToken and u.isDeleted = false and u.status = true""")
    Optional<UserEntity> findByVerifiedTokenAndIsDeletedFalseAndStatusTrue(@Param("verifiedToken") String verifiedToken);

    @Query("select u from UserEntity u where u.email = :email and u.isDeleted = false")
    Optional<UserEntity> findByEmailAndIsDeletedFalse(@Param("email") String email);

    @Query("""
            select (count(u) > 0) from UserEntity u
            where u.verifiedToken = :verifiedToken and u.isDeleted = false and u.status = true""")
    boolean existsByVerifiedTokenAndIsDeletedFalseAndStatusTrue(@Param("verifiedToken") String verifiedToken);
}
