package com.pragya.ats_analyzer_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pragya.ats_analyzer_backend.dto.AnalyzeResponse;

@Service
public class ResumeService {

    @Autowired
    private PdfService pdfService;

    @Autowired
    private GroqService groqService;

    private final Gson gson = new Gson();

    public AnalyzeResponse analyzeResume(
            MultipartFile resume,
            String jobDescription
    ) throws Exception {

        String resumeText = pdfService.extractText(resume);

        String groqResponse =
                groqService.analyzeResume(
                        resumeText,
                        jobDescription
                );

        System.out.println(groqResponse);

        JsonObject root =
                JsonParser.parseString(groqResponse).getAsJsonObject();

        String content =
                root.getAsJsonArray("choices")
                    .get(0)
                    .getAsJsonObject()
                    .getAsJsonObject("message")
                    .get("content")
                    .getAsString();

        content = content
                .replace("```json", "")
                .replace("```", "")
                .trim();

        AnalyzeResponse response =
                gson.fromJson(content, AnalyzeResponse.class);
     // =========================
     // Calculate Skill Score
     // =========================

     int matched = response.getMatchedSkills().size();
     int missing = response.getMissingSkills().size();

     int total = matched + missing;

     int skillScore = 0;

     if (total > 0) {
         skillScore = (matched * 100) / total;
     }

     response.setSkillScore(skillScore);

     // =========================
     // Experience Score
     // =========================

     int experienceScore =
             response.getExperience() != null &&
             !response.getExperience().isBlank()
             ? 100 : 0;

     response.setExperienceScore(experienceScore);

     // =========================
     // Education Score
     // =========================

     int educationScore =
             response.getEducation() != null &&
             !response.getEducation().isBlank()
             ? 100 : 0;

     response.setEducationScore(educationScore);

     // =========================
     // Resume Quality Score
     // =========================

     int qualityScore = 90;

     response.setQualityScore(qualityScore);

     // =========================
     // Final Weighted ATS Score
     // =========================

     int finalScore =
             (int) (
                     skillScore * 0.6 +
                     experienceScore * 0.2 +
                     educationScore * 0.1 +
                     qualityScore * 0.1
             );

     response.setResumeScore(finalScore);

     // =========================
     // Recommendation
     // =========================

     if (finalScore >= 90) {
         response.setRecommendation("Excellent Match");
     }
     else if (finalScore >= 75) {
         response.setRecommendation("Good Match");
     }
     else if (finalScore >= 60) {
         response.setRecommendation("Average Match");
     }
     else {
         response.setRecommendation("Needs Improvement");
     }

     return response;
    }
}