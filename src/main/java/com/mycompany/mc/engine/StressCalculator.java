package com.mycompany.mc.engine;

import java.util.*;

public class StressCalculator {

    private static final List<String> HIGH_STRESS = Arrays.asList(
        "deadline", "exam", "test", "assignment", "overwhelmed", "no sleep",
        "sleepless", "can't sleep", "panic", "failing", "pressure", "anxious",
        "can't focus", "broke", "fight", "argument", "crying", "hopeless",
        "giving up", "can't do this", "too much", "burden"
    );

    private static final List<String> LOW_STRESS = Arrays.asList(
        "relaxed", "calm", "peaceful", "good day", "went well", "happy",
        "enjoyed", "rested", "great", "accomplished", "proud", "relieved"
    );

    public int calculate(String text, Map<String, Integer> emotions) {
        String lower = text.toLowerCase();
        int score = 30; // baseline

        for (String hw : HIGH_STRESS) {
            if (lower.contains(hw)) score += 8;
        }
        for (String lw : LOW_STRESS) {
            if (lower.contains(lw)) score -= 6;
        }

        // Emotion influence
        score += (emotions.getOrDefault("sadness", 0) * 0.2);
        score += (emotions.getOrDefault("anger",   0) * 0.15);
        score += (emotions.getOrDefault("fear",    0) * 0.2);
        score -= (emotions.getOrDefault("joy",     0) * 0.15);

        return Math.max(0, Math.min(100, score));
    }
}