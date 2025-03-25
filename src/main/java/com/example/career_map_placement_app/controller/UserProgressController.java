package com.example.career_map_placement_app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.career_map_placement_app.model.JwtUtil;
import com.example.career_map_placement_app.model.UserProgress;
import com.example.career_map_placement_app.repository.UserProgressRepository;
@RestController
@RequestMapping("/user-progress")
public class UserProgressController {

    @Autowired
    private UserProgressRepository progressRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/mark-solved")
    public ResponseEntity<?> markQuestionAsSolved(@RequestBody UserProgress progress,
                                                  @RequestHeader("Authorization") String token) {
        String userId = jwtUtil.extractUserId(token.substring(7));
        progress.setUserId(userId);
        progressRepository.save(progress);
        return ResponseEntity.ok("Marked as solved");
    }

    @GetMapping("/solved-questions/{companyId}")
    public ResponseEntity<?> getSolvedQuestions(@PathVariable Long companyId, @RequestHeader("Authorization") String token) {
        String userId = jwtUtil.extractUserId(token.substring(7));
        return ResponseEntity.ok(progressRepository.findByUserIdAndCompanyId(userId, companyId));
    }
}

