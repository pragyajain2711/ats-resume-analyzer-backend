package com.pragya.ats_analyzer_backend.dto;

public class AnalyzeRequest {

    private String jobDescription;

    public AnalyzeRequest() {
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}