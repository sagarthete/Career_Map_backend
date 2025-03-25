package com.example.career_map_placement_app.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;
    @ElementCollection
    private List<String> options;
    
    private String answer;

    private String questionType;
    
    @Lob
    private String codingSolution;

    @ManyToOne
    @JoinColumn(name = "aptitude_id")
    @JsonIgnore
    private Aptitude aptitude;

    @ManyToOne
    @JoinColumn(name = "interview_id")
    @JsonIgnore
    private InterviewTopic interview;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Aptitude getAptitude() {
        return aptitude;
    }

    public void setAptitude(Aptitude aptitude) {
        this.aptitude = aptitude;
    }

    public InterviewTopic getInterview() {
        return interview;
    }

    public void setInterview(InterviewTopic interview) {
        this.interview = interview;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getCodingSolution() {
        return codingSolution;
    }

    public void setCodingSolution(String codingSolution) {
        this.codingSolution = codingSolution;
    }

    
}
