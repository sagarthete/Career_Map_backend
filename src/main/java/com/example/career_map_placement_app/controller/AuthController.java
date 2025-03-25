package com.example.career_map_placement_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.career_map_placement_app.services.AuthService;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Map<String, String> user) {
        String message = authService.registerUser(user.get("username"), user.get("password"));
        return ResponseEntity.ok(Collections.singletonMap("message", message));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> user) {
        String token = authService.loginUser(user.get("username"), user.get("password"));
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}
