# MindCheck — Mental Wellness Check-In Assistant

MindCheck is a rule-based mental wellness journal application built with Spring Boot.
It analyzes a user's daily journal entry and returns emotionally intelligent,
supportive feedback without any external AI APIs. All analysis is done using
keyword-based rule engines written in pure Java.

---

## Important Disclaimer

This application is NOT a medical tool. It does not diagnose, treat, or assess
any mental health condition. It is intended purely as a self-reflection aid for
students and general users. If you are experiencing a mental health crisis, please
contact a licensed professional or a helpline.

---

## Features

- Emotion detection across four dimensions: sadness, joy, anger, and fear
- Rule-based stress scoring from 0 to 100
- Stress trend analysis comparing today's score with the previous session
- Explainable reasoning based on journal tone and keyword patterns
- Empathetic support messages tailored to detected emotional state
- Actionable coping suggestions suited to a student lifestyle
- Safety check that gently flags crisis-level language and provides helpline info

---

## Tech Stack

- Java 17
- Spring Boot 3.2.4
- Apache Tomcat (embedded)
- Maven
- Vanilla HTML, CSS, and JavaScript (no frontend framework)
- Apache NetBeans IDE

---

## Project Structure
```
mc/
├── src/
│   ├── main/
│   │   ├── java/com/mycompany/mc/
│   │   │   ├── Mc.java
│   │   │   ├── controller/
│   │   │   │   └── WellnessController.java
│   │   │   ├── engine/
│   │   │   │   ├── EmotionDetector.java
│   │   │   │   ├── StressCalculator.java
│   │   │   │   └── ResponseGenerator.java
│   │   │   ├── model/
│   │   │   │   └── JournalEntry.java
│   │   │   └── service/
│   │   │       └── WellnessAnalyzer.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   │           └── index.html
└── pom.xml
```

---

## How It Works

### EmotionDetector
Scans the journal text for keywords mapped to four emotions. Each matched keyword
adds to that emotion's score, which is then scaled to a value between 0 and 100.

### StressCalculator
Starts at a baseline score of 30 and adjusts upward for high-stress keywords such
as "exam", "deadline", "overwhelmed", and "no sleep". It adjusts downward for
positive keywords like "relaxed" and "accomplished". Emotion scores are then blended
in to produce a final stress score between 0 and 100.

### ResponseGenerator
Uses the dominant emotion, stress score, and previous stress score to generate six
output sections using conditional logic and switch statements. It also checks for
crisis-level phrases and returns a gentle safety note with helpline information if
any are detected.

---

## API Reference

### POST /api/analyze

Request body:
```json
{
  "text": "I have exams coming up and I barely slept. I feel so overwhelmed.",
  "previousStressScore": 40
}
```

Response body:
```json
{
  "emotionScores": {
    "sadness": 20,
    "joy": 0,
    "anger": 0,
    "fear": 40
  },
  "stressScore": 72,
  "previousStressScore": 40,
  "emotionalInsight": "...",
  "trend": "Increasing — ...",
  "why": "...",
  "supportMessage": "...",
  "suggestions": "1. ...\n2. ...",
  "supportNote": "..."
}
```

The "supportNote" field is only present when crisis-level language is detected.

---

## Setup and Running

### Prerequisites

- JDK 17 or higher
- Apache NetBeans 21 or higher
- Maven (bundled with NetBeans)

### Steps

1. Open the project in Apache NetBeans
2. Right-click the project and select Clean and Build
3. Wait for Maven to download all dependencies
4. Right-click the project and select Run
5. Open a browser and navigate to http://localhost:8081

If port 8081 is already in use, change the port in application.properties:
```
server.port=8082
```

---

## Usage

1. Open http://localhost:8081 in your browser
2. Type a journal entry describing how your day went
3. Enter your stress score from the previous session (default is 40 if first use)
4. Click Analyse My Day
5. Review your emotional insight, stress trend, support message, and suggestions

---

## Crisis Resources (India)

iCall: 9152987821
Vandrevala Foundation Helpline: 1860-2662-345 (available 24 hours, 7 days a week)

---

