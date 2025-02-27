package com.example.demo.Repository;


import com.example.demo.Entity.Referral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReferralRepository extends JpaRepository<Referral, Long> {
    List<Referral> findByReferrerId(Long referrerId);
    long countByReferrerId(Long referrerId);
}

