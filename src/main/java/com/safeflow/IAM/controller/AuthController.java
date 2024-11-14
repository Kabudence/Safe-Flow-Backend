package com.safeflow.IAM.controller;

import com.safeflow.IAM.dto.SigninRequest;
import com.safeflow.IAM.dto.SignupRequest;
import com.safeflow.IAM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        return ResponseEntity.ok(userService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody SigninRequest request) {
        try {
            boolean isAuthenticated = userService.signin(request);
            return isAuthenticated ? ResponseEntity.ok("Login successful") : ResponseEntity.status(401).body("Unauthorized");
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
