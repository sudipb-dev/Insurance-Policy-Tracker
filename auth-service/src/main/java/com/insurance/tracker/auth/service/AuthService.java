package com.insurance.tracker.auth.service;

import com.insurance.tracker.auth.dto.AuthRequests;
import com.insurance.tracker.auth.model.User;
import com.insurance.tracker.auth.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(AuthRequests.Register req) {
        User u = new User();
        u.setName(req.name);
        u.setEmail(req.email);
        u.setPasswordHash(encoder.encode(req.password));
        u.setRole(req.role == null ? "AGENT" : req.role);
        return userRepository.save(u);
    }

    public Optional<User> authenticate(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(u -> encoder.matches(password, u.getPasswordHash()));
    }
}
