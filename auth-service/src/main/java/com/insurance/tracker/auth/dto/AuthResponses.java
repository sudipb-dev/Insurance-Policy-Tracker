package com.insurance.tracker.auth.dto;

public class AuthResponses {
    public static class LoginResponse {
        public String token;
        public String role;
        public Long userId;
    }
}
