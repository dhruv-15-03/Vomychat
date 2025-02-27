package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "referrals")
public class Referral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The user who referred someone
    @ManyToOne(optional = false)
    @JoinColumn(name = "referrer_id")
    private User referrer;

    // The newly registered (referred) user
    @ManyToOne(optional = false)
    @JoinColumn(name = "referred_user_id")
    private User referredUser;

    private LocalDateTime createdAt = LocalDateTime.now();


    public Referral(User referrer, User referredUser) {
        this.referrer = referrer;
        this.referredUser = referredUser;
    }
}

