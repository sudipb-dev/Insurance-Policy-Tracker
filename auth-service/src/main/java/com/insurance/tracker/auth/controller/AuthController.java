package com.insurance.tracker.auth.controller;

import com.insurance.tracker.auth.dto.AuthRequests;
import com.insurance.tracker.auth.dto.AuthResponses;
import com.insurance.tracker.auth.model.User;
import com.insurance.tracker.auth.service.AuthService;
import com.insurance.tracker.auth.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequests.Register req) {
        User u = authService.register(req);
        return ResponseEntity.ok(u);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequests.Login req) {
        return authService.authenticate(req.email, req.password)
                .map(u -> {
                    String token = jwtUtil.generateToken(u.getId(), u.getRole());
                    AuthResponses.LoginResponse res = new AuthResponses.LoginResponse();
                    res.token = token;
                    res.role = u.getRole();
                    res.userId = u.getId();
                    return ResponseEntity.ok(res);
                })
                .orElse(ResponseEntity.status(401).body("Invalid credentials"));
    }
}
