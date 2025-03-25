package com.example.career_map_placement_app.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.career_map_placement_app.model.Aptitude;
import com.example.career_map_placement_app.model.Company;
import com.example.career_map_placement_app.model.GroupDiscussion;
import com.example.career_map_placement_app.model.InterviewTopic;
import com.example.career_map_placement_app.model.Lecture;
import com.example.career_map_placement_app.model.Question;
import com.example.career_map_placement_app.model.Quiz;
import com.example.career_map_placement_app.repository.CompanyRepository;
import com.example.career_map_placement_app.services.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

   
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        // Ensure parent references are set correctly for all child entities before saving

        for (Aptitude aptitudeTopic : company.getAptitudeTopics()) {
            aptitudeTopic.setCompany(company);
            for (Lecture lecture : aptitudeTopic.getLectures()) {
                lecture.setAptitude(aptitudeTopic);
            }
            for (Question question : aptitudeTopic.getQuestions()) {
                question.setAptitude(aptitudeTopic);
            }
            for (Quiz quiz : aptitudeTopic.getQuizzes()) {
                quiz.setAptitude(aptitudeTopic);
            }
        }

        for (GroupDiscussion groupDiscussionTopic : company.getGroupDiscussionTopics()) {
            groupDiscussionTopic.setCompany(company);
        }

        for (InterviewTopic interviewTopic : company.getInterviewTopics()) {
            interviewTopic.setCompany(company);
            
            for (Question interviewQuestion : interviewTopic.getQuestions()) {
                interviewQuestion.setInterview(interviewTopic);
            }
        }

        // Save company and all nested entities
        companyRepository.save(company);
        return new ResponseEntity<>(company, HttpStatus.CREATED);
    }


    @PutMapping("/{companyName}")
public ResponseEntity<?> updateCompany(@PathVariable String companyName, @RequestBody Company updatedCompany) {
    Optional<Company> existingCompanyOptional = companyRepository.findByName(companyName);
    
    if (!existingCompanyOptional.isPresent()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("message", "Company not found"));
    }

    Company existingCompany = existingCompanyOptional.get();

    // ✅ Update simple fields
    existingCompany.setDescription(updatedCompany.getDescription());
    existingCompany.setVisitingYear(updatedCompany.getVisitingYear());
    existingCompany.setVisitingMonth(updatedCompany.getVisitingMonth());
    existingCompany.setServiceFlag(updatedCompany.isServiceFlag());
    existingCompany.setFounder(updatedCompany.getFounder());
    existingCompany.setSince(updatedCompany.getSince());
    existingCompany.setRevenue(updatedCompany.getRevenue());
    existingCompany.setLocation(updatedCompany.getLocation());
    existingCompany.setAboutAptitude(updatedCompany.getAboutAptitude());
    existingCompany.setAboutInterview(updatedCompany.getAboutInterview());
    existingCompany.setAboutGd(updatedCompany.getAboutGd());

    existingCompany.getAptitudeTopics().clear();
    for (Aptitude aptitude : updatedCompany.getAptitudeTopics()) {
        aptitude.setCompany(existingCompany);
        
        for (Lecture lecture : aptitude.getLectures()) {
            lecture.setAptitude(aptitude);
        }
        for (Question question : aptitude.getQuestions()) {
            question.setAptitude(aptitude);
        }
        for (Quiz quiz : aptitude.getQuizzes()) {
            quiz.setAptitude(aptitude);
        }

        existingCompany.getAptitudeTopics().add(aptitude);
    }

    // Clear and update Group Discussion Topics
    existingCompany.getGroupDiscussionTopics().clear();
    for (GroupDiscussion gdTopic : updatedCompany.getGroupDiscussionTopics()) {
        gdTopic.setCompany(existingCompany);
        existingCompany.getGroupDiscussionTopics().add(gdTopic);
    }

    // Clear and update Interview Topics
    existingCompany.getInterviewTopics().clear();
    for (InterviewTopic interviewTopic : updatedCompany.getInterviewTopics()) {
        interviewTopic.setCompany(existingCompany);
        for (Question question : interviewTopic.getQuestions()) {
            question.setInterview(interviewTopic);
        }
        existingCompany.getInterviewTopics().add(interviewTopic);
    }

    // ✅ Save updated entity
    companyRepository.save(existingCompany);

    return ResponseEntity.ok(Collections.singletonMap("message", "Company updated successfully"));
}


    // ✅ Delete Company by Name (DELETE request)
    @DeleteMapping("/{companyName}")
    public ResponseEntity<String> deleteCompany(@PathVariable String companyName) {
    // Find company by name
    Company company = companyRepository.findByName(companyName)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));

    // Delete company
    companyRepository.delete(company);

    return ResponseEntity.ok("Company '" + companyName + "' deleted successfully.");
}

}
