package com.example.demo.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password; // hashed password

    @Column(nullable = false)
    private String role; // e.g., "USER", "ADMIN"

    // Optional referral fields:
    private String referralCode;
    private String referredBy;


    public User(String username, String email, String password, String role, String referralCode, String referredBy) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.referralCode = referralCode;
        this.referredBy = referredBy;
    }


}


