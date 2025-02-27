package com.example.demo.Controller;

import com.example.demo.DTO.ReferralDto;
import com.example.demo.DTO.ReferralStatsDto;
import com.example.demo.Entity.Referral;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.ReferralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ReferralController {

    @Autowired
    private ReferralService referralService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/createReferral")
    public Referral createReferral(@RequestParam String referralCode,
                                   @RequestParam Long referredUserId) {
        User referredUser = userRepository.findById(referredUserId)
                .orElseThrow(() -> new RuntimeException("Referred user not found"));
        return referralService.createReferral(referralCode, referredUser);
    }
    @GetMapping
    public ResponseEntity<List<ReferralDto>> getReferralsForLoggedInUser(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Referral> referrals = referralService.getReferralsForUser(user);

        // Map Referral to ReferralDto
        List<ReferralDto> referralDtos = referrals.stream()
                .map(ref -> new ReferralDto(
                        ref.getReferrer().getUsername(),
                        ref.getReferredUser().getUsername(),
                        ref.getReferredUser().getEmail()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(referralDtos);
    }
    @GetMapping("/stats")
    public ResponseEntity<ReferralStatsDto> getReferralStats(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ReferralStatsDto stats = referralService.getReferralStatsForUser(user);
        return ResponseEntity.ok(stats);
    }
}

