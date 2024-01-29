package com.example.demo.base;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

/**
 * @author Sombath
 * create at 23/10/22 6:23 AM
 */

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "created_at",nullable = false,updatable = false)
    @CreatedDate
    @CurrentTimestamp
    private Instant createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    @CurrentTimestamp
    private Instant updatedAt;
}