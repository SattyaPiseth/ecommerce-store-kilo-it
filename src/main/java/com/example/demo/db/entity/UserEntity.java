package com.example.demo.db.entity;

import com.example.demo.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_user_username", columnList = "username", unique = true),
        @Index(name = "idx_user_email", columnList = "email", unique = true),
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@EntityListeners(AuditingEntityListener.class)
public class UserEntity extends BaseEntity{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;

    @Column(unique = true)
    private String uuid;

    @Size(max = 255)
    @NotNull
    @Column(name = "username",nullable = false)
    private String username;

    @JsonIgnore
    @Size(max = 100)
    @Column(name = "password", nullable = false)
    private String password;

    @Size(max = 100)
    @Column(name = "email",unique = true,nullable = false)
    private String email;

    @Size(max = 100)
    @Column(name = "name")
    private String name;

    @Size(max = 200)
    @Column(name = "bio")
    private String bio;

    @Size(max = 200)
    @Column(name = "avatar")
    private String avatar;

    @Size(max = 200)
    @Column(name = "address")
    private String address;

    @Size(max = 50)
    @Column(name = "phone")
    private String phone;

    @Column(columnDefinition = "tinyint(1) default 1")
    private Boolean status = Boolean.TRUE;

    @Size(max = 6)
    @Column(name = "6_digits")
    private String verifiedCode;

    @Column(name = "verification_token")
    private String verifiedToken;


    @Column(name = "verified",columnDefinition = "boolean default false")
    private Boolean isVerified;

    @Column(name = "deleted",columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity roleEntity;

    @JoinColumn(name = "created_by")
    @ManyToOne(fetch = FetchType.LAZY)
    @CreatedBy
    private UserEntity createdBy;

    @JoinColumn(name = "updated_by")
    @ManyToOne
    @LastModifiedBy
    private UserEntity updatedBy;

//    @Column(name = "created_at")
//    @CreatedDate
//    @CurrentTimestamp
//    private Instant createdAt;
//
//    @Column(name = "updated_at")
//    @LastModifiedDate
//    @CurrentTimestamp
//    private Instant updatedAt;
}