package com.insurance.tracker.auth.dto;

public class AuthRequests {
    public static class Register {
        public String name;
        public String email;
        public String password;
        public String role; // AGENT or ADMIN
    }

    public static class Login {
        public String email;
        public String password;
    }
}
