package com.pragya.ats_analyzer_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pragya.ats_analyzer_backend.dto.AnalyzeResponse;
import com.pragya.ats_analyzer_backend.service.ResumeService;

@RestController
@RequestMapping("/api")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @PostMapping(
            value = "/analyze",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public AnalyzeResponse analyzeResume(
            @RequestParam("resume") MultipartFile resume,
            @RequestParam("jobDescription") String jobDescription
    ) throws Exception {

        return resumeService.analyzeResume(resume, jobDescription);
    }
}