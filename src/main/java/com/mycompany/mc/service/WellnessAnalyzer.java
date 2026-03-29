package com.mycompany.mc.service;

import com.mycompany.mc.engine.*;
import com.mycompany.mc.model.JournalEntry;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class WellnessAnalyzer {

    private final EmotionDetector emotionDetector = new EmotionDetector();
    private final StressCalculator stressCalculator = new StressCalculator();
    private final ResponseGenerator responseGenerator = new ResponseGenerator();

    public Map<String, Object> analyze(JournalEntry entry) {
        String text = entry.getText();
        int prevScore = entry.getPreviousStressScore();

        Map<String, Integer> emotions = emotionDetector.detect(text);
        int stressScore = stressCalculator.calculate(text, emotions);
        Map<String, String> response = responseGenerator.generate(text, emotions, stressScore, prevScore);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("emotionScores", emotions);
        result.put("stressScore", stressScore);
        result.put("previousStressScore", prevScore);
        result.putAll(response);
        return result;
    }
}