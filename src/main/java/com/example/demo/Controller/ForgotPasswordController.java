package com.example.demo.Controller;

import com.example.demo.DTO.ForgotPasswordRequest;
import com.example.demo.Service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ForgotPasswordController {

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        try {
            passwordResetService.createPasswordResetToken(request.getEmail());
            return ResponseEntity.ok("Password reset instructions sent to your email, if it exists in our system.");
        } catch (Exception e) {
            return ResponseEntity.ok("Password reset instructions sent to your email, if it exists in our system.");
        }
    }
}

