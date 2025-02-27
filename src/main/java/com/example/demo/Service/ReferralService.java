package com.example.demo.Service;

import com.example.demo.DTO.ReferralStatsDto;
import com.example.demo.Entity.Referral;
import com.example.demo.Entity.User;
import com.example.demo.Repository.ReferralRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferralService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReferralRepository referralRepository;

    public Referral createReferral(String referralCode, User referredUser) {
        User referrer = userRepository.findByReferralCode(referralCode)
                .orElseThrow(() -> new RuntimeException("Invalid referral code"));
        Referral referral = new Referral(referrer, referredUser);
        return referralRepository.save(referral);
    }
    public List<Referral> getReferralsForUser(User referrer) {
        return referralRepository.findByReferrerId(referrer.getId());
    }
    public ReferralStatsDto getReferralStatsForUser(User referrer) {
        long totalReferrals = referralRepository.countByReferrerId(referrer.getId());
        return new ReferralStatsDto(totalReferrals);
    }
}

