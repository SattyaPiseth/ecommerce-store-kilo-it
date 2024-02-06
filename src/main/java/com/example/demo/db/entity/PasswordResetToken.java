package com.example.demo.db.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Sattya
 * create at 1/31/2024 11:45 PM
 */
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "password_reset_token")
public class PasswordResetToken {
    private static final int EXPIRATION = 60 * 24;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;

    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserEntity user;

    private Date expiryDate;

    public PasswordResetToken(){
        this.expiryDate = calculateExpiryDate();
    }
    private Date calculateExpiryDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, PasswordResetToken.EXPIRATION);
        return new Date(calendar.getTime().getTime());
    }
}
