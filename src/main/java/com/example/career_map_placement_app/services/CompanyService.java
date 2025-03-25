package com.example.career_map_placement_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.career_map_placement_app.model.Company;
import com.example.career_map_placement_app.repository.CompanyRepository;

import jakarta.transaction.Transactional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    public List<Company> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        for (Company company : companies) {
            company.getAptitudeTopics().size();  // Force fetch
            company.getGroupDiscussionTopics().size();
            company.getInterviewTopics().size();
        }
        return companies;
    }

    public Company updateCompanyByName(String name, Company updatedCompany) {
        return companyRepository.findByName(name)
                .map(existingCompany -> {
                    existingCompany.setDescription(updatedCompany.getDescription());
                    existingCompany.setAptitudeTopics(updatedCompany.getAptitudeTopics());
                    existingCompany.setGroupDiscussionTopics(updatedCompany.getGroupDiscussionTopics());
                    existingCompany.setInterviewTopics(updatedCompany.getInterviewTopics());
                    return companyRepository.save(existingCompany);  // 🔹 Save updated company
                })
                .orElseThrow(() -> new RuntimeException("Company not found with name: " + name));
    }

    // ✅ Delete Company by Name
    public void deleteCompanyByName(String name) {
        companyRepository.findByName(name)
                .ifPresentOrElse(
                        company -> companyRepository.delete(company),  // 🔹 Delete if exists
                        () -> {
                            throw new RuntimeException("Company not found with name: " + name);
                        }
                );
    }
}
