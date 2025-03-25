package com.example.career_map_placement_app.model;



import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Lob
    private String description;
    
    private String visitingYear;
    private String visitingMonth;
    private boolean serviceFlag;
    private String founder;
    private String since;
    private String revenue;
    private String location;

    @Lob
    private String aboutAptitude;

    @Lob
    private String aboutInterview;

    @Lob
    private String aboutGd; 

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Aptitude> aptitudeTopics = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<GroupDiscussion> groupDiscussionTopics = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<InterviewTopic> interviewTopics = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Aptitude> getAptitudeTopics() {
        return aptitudeTopics;
    }

    public void setAptitudeTopics(List<Aptitude> aptitudeTopics) {
        this.aptitudeTopics = aptitudeTopics;
    }

    public List<GroupDiscussion> getGroupDiscussionTopics() {
        return groupDiscussionTopics;
    }

    public void setGroupDiscussionTopics(List<GroupDiscussion> groupDiscussionTopics) {
        this.groupDiscussionTopics = groupDiscussionTopics;
    }

    public List<InterviewTopic> getInterviewTopics() {
        return interviewTopics;
    }

    public void setInterviewTopics(List<InterviewTopic> interviewTopics) {
        this.interviewTopics = interviewTopics;
    }

    public String getVisitingYear() {
        return visitingYear;
    }

    public void setVisitingYear(String visitingYear) {
        this.visitingYear = visitingYear;
    }

    public String getVisitingMonth() {
        return visitingMonth;
    }

    public void setVisitingMonth(String visitingMonth) {
        this.visitingMonth = visitingMonth;
    }

    public boolean isServiceFlag() {
        return serviceFlag;
    }

    public void setServiceFlag(boolean serviceFlag) {
        this.serviceFlag = serviceFlag;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAboutAptitude() {
        return aboutAptitude;
    }

    public void setAboutAptitude(String aboutAptitude) {
        this.aboutAptitude = aboutAptitude;
    }

    public String getAboutInterview() {
        return aboutInterview;
    }

    public void setAboutInterview(String aboutInterview) {
        this.aboutInterview = aboutInterview;
    }

    public String getAboutGd() {
        return aboutGd;
    }

    public void setAboutGd(String aboutGd) {
        this.aboutGd = aboutGd;
    }
    
    
}
