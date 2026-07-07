package com.pragya.ats_analyzer_backend.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GroqService {

    @Value("${groq.api.key}")
    private String apiKey;

    private static final String URL =
            "https://api.groq.com/openai/v1/chat/completions";

    public String analyzeResume(String resumeText, String jobDescription) throws Exception {

        RestTemplate restTemplate = new RestTemplate();

        String prompt = """
        		You are an expert ATS Resume Analyzer.

        		Analyze the following resume against the job description.

        		IMPORTANT RULES:

        		1. Read the COMPLETE resume carefully.
        		2. Extract ALL technical skills mentioned in the resume.
        		3. Do NOT invent skills.
        		4. If a skill exists anywhere in the resume, include it.
        		5. Compare every extracted skill with the Job Description.
        		6. Calculate ATS score realistically.

        		Resume:
        		%s

        		Job Description:
        		%s

        		Return ONLY valid JSON in this format.

       {
  "resumeScore":0,
  "recommendation":"Good Match",

  "name":"",
  "email":"",
  "phone":"",
  "education":"",
  "experience":"",

  "professionalSummary":"",

  "matchedSkills":[
    "Java",
    "Spring Boot",
    "React",
    "REST APIs",
    "SQL",
    "Git",
    "Postman"
  ],

  "missingSkills":[
    "Docker",
    "AWS",
    "Kubernetes"
  ],

  "strengths":[
    "Strong Full Stack Development",
    "Internship Experience",
    "Leadership Experience"
  ],

  "suggestions":[
    "Add Docker experience",
    "Mention measurable achievements",
    "Add cloud technologies"
  ]
  
  "interviewQuestions":[
  "Question 1",
  "Question 2",
  "Question 3",
  "Question 4",
  "Question 5"
]
}
        		Return ONLY JSON.
        		No markdown.
        		No explanation.
        		""".formatted(resumeText, jobDescription);

        Map<String, Object> body = Map.of(
                "model", "llama-3.3-70b-versatile",
                "messages", List.of(
                        Map.of(
                                "role", "user",
                                "content", prompt
                        )
                ),
                "temperature", 0.2
        );

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(body);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                URL,
                HttpMethod.POST,
                entity,
                String.class
        );

        return response.getBody();
    }
}