package com.example.demo;


import com.example.demo.DTO.RegisterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ReferralControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Test registration with an invalid referral code.
    // In this example, if the referral code is invalid, our service might throw an exception.
    // Depending on your implementation, you might return a specific error.
    @Test
    @Order(1)
    public void testRegisterUser_InvalidReferralCode() throws Exception {
        RegisterRequest request = new RegisterRequest();
        request.setUsername("userWithInvalidReferral");
        request.setEmail("user2@example.com");
        request.setPassword("password123");
        request.setReferredBy("INVALID_CODE");

        // Depending on your logic, registration might still succeed if you decide to ignore invalid codes.
        // Adjust the expected response accordingly.
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully!"));
    }

    // Test referral statistics endpoint. This test assumes that a valid JWT token for "testuser" is available.
    // In a real scenario, you would authenticate and capture the JWT token.
    @Test
    @Order(2)
    public void testReferralStats() throws Exception {
        // Replace "your_valid_jwt_token" with a valid token obtained during testing.
        String token = "Bearer your_valid_jwt_token";

        mockMvc.perform(get("/api/referrals/stats")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalReferrals").isNumber());
    }

    // Optionally, you can add a test for self-referral (if your business logic should reject it).
    // For example:
    // @Test
    // public void testRegisterUser_SelfReferral() throws Exception {
    //     // Register a user normally first.
    //     RegisterRequest request = new RegisterRequest();
    //     request.setUsername("selfRefUser");
    //     request.setEmail("selfref@example.com");
    //     request.setPassword("password123");
    //     request.setReferredBy("");
    //
    //     mockMvc.perform(post("/api/auth/register")
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .content(objectMapper.writeValueAsString(request)))
    //             .andExpect(status().isOk());
    //
    //     // Attempt to register with a referral code equal to the user's own referral code.
    //     // This requires fetching the user's referral code from the database. For simplicity, assume the logic returns an error.
    //     // You can simulate this by sending the same referral code and expecting an error response.
    // }
}

