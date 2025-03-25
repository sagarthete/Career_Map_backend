package com.example.career_map_placement_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.career_map_placement_app.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT c FROM Company c LEFT JOIN FETCH c.aptitudeTopics LEFT JOIN FETCH c.groupDiscussionTopics LEFT JOIN FETCH c.interviewTopics")
    List<Company> findAllWithDetails();

    Optional<Company> findByName(String name);  // 🔹 Find company by name
    void deleteByName(String name); 
}


