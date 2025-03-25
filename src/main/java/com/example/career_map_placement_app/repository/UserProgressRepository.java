package com.example.career_map_placement_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.career_map_placement_app.model.UserProgress;

import java.util.List;

public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {
    List<UserProgress> findByUserIdAndCompanyId(String userId, Long companyId);
    boolean existsByUserIdAndCompanyIdAndQuestionId(String userId, Long companyId, Long questionId);
}
