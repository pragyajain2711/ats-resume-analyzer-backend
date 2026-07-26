# 🚀 ATS Resume Analyzer - Backend

This is the backend service for the **AI ATS Resume Analyzer**. It is built using **Spring Boot** and integrates with the **Groq AI API** to analyze resumes against job descriptions, calculate ATS compatibility scores, extract resume details, identify missing keywords, and generate AI-powered recommendations.

---

## 🌐 Live Backend

**Railway Deployment**

https://ats-resume-analyzer-backend-production.up.railway.app

---

## 📌 Features

- 📄 PDF Resume Parsing using Apache PDFBox
- 🤖 AI Resume Analysis with Groq API
- 📊 ATS Resume Score Calculation
- 🎯 Skill Matching
- ❌ Missing Skills Detection
- 💡 AI Suggestions for Resume Improvement
- 📚 Resume Information Extraction
- ⭐ Resume Strength Analysis
- 📈 Overall Recommendation
- 🔗 REST API Integration with React Frontend
- 🌍 CORS Configuration for Production Deployment

---

# 🛠 Tech Stack

- Java 21
- Spring Boot 3
- Maven
- Apache PDFBox
- Gson
- REST API
- Groq AI API
- Railway
- Git & GitHub

---

# 📂 Project Structure

```text
src
│
├── controller
│     └── ResumeController.java
│
├── service
│     ├── ResumeService.java
│     ├── GroqService.java
│     └── PdfService.java
│
├── dto
│     └── AnalyzeResponse.java
│
├── config
│     └── CorsConfig.java
│
├── resources
│     └── application.properties
│
└── AtsAnalyzerBackendApplication.java
```

---

# 📡 API Endpoint

## Analyze Resume

**POST**

```text
/api/analyze
```

### Request

Content-Type

```text
multipart/form-data
```

Parameters

| Parameter | Type | Description |
|-----------|------|-------------|
| resume | PDF File | Resume |
| jobDescription | String | Job Description |

---

### Response

Example

```json
{
  "resumeScore": 84,
  "skillScore": 90,
  "experienceScore": 100,
  "educationScore": 100,
  "qualityScore": 90,
  "matchedSkills": [
    "Java",
    "Spring Boot",
    "REST API"
  ],
  "missingSkills": [
    "Docker",
    "Kubernetes"
  ],
  "suggestions": [
    "Add cloud experience",
    "Highlight measurable achievements"
  ],
  "recommendation": "Good Match"
}
```

---

# ⚙️ Environment Variables

Create an `application.properties` file and add:

```properties
groq.api.key=${GROQ_API_KEY}
```

Set the environment variable:

```text
GROQ_API_KEY=your_groq_api_key
```

---

# ▶️ Running Locally

Clone the repository

```bash
git clone https://github.com/pragyajain2711/ats-resume-analyzer-backend.git
```

Go inside the project

```bash
cd ats-resume-analyzer-backend
```

Build the project

```bash
./mvnw clean install
```

Run the application

```bash
./mvnw spring-boot:run
```

The backend starts on

```text
http://localhost:8080
```

---

# 🔗 Frontend Repository

https://github.com/pragyajain2711/ats-resume-analyzer-frontend

---

# 🚀 Deployment

Backend is deployed using **Railway**.

Frontend is deployed using **Vercel**.

---

# 🔮 Future Improvements

- User Authentication (JWT)
- Resume History
- Database Integration (MySQL)
- Multiple Resume Comparison
- DOCX Resume Support
- AI Interview Question Generator
- Admin Dashboard
- Resume Analytics Dashboard

---

# 👩‍💻 Author

**Pragya Jain**

### GitHub

https://github.com/pragyajain2711

---

## ⭐ Support

If you found this project useful, consider giving it a ⭐ on GitHub.
