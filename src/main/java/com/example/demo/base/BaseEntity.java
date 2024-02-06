package com.example.demo.base;


import com.example.demo.db.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Date;

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

    @Temporal(TemporalType.TIMESTAMP)
    @CurrentTimestamp
    @Column(name = "created_at",nullable = false,updatable = false)
    @CreatedDate
    private Instant createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    @LastModifiedDate
    private Date updatedAt;

    @JoinColumn(name = "created_by")
    @ManyToOne(fetch = FetchType.LAZY)
    @CreatedBy
    private UserEntity createdBy;

    @JoinColumn(name = "updated_by")
    @ManyToOne
    @LastModifiedBy
    private UserEntity updatedBy;
}