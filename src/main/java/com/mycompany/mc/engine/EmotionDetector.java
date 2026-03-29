package com.mycompany.mc.engine;

import java.util.*;

public class EmotionDetector {

    private static final List<String> SAD_WORDS = Arrays.asList(
        "sad", "crying", "hopeless", "alone", "lonely", "depressed", "miserable",
        "unhappy", "lost", "grief", "tears", "heartbroken", "empty", "worthless",
        "disappointed", "gloomy", "numb", "exhausted", "tired", "failed", "failure"
    );

    private static final List<String> JOY_WORDS = Arrays.asList(
        "happy", "excited", "great", "amazing", "good", "wonderful", "joyful",
        "fantastic", "glad", "cheerful", "blessed", "grateful", "love", "positive",
        "celebrate", "fun", "enjoyed", "smile", "laughed", "proud", "accomplished"
    );

    private static final List<String> ANGER_WORDS = Arrays.asList(
        "angry", "furious", "frustrated", "annoyed", "irritated", "mad", "hate",
        "rage", "upset", "bitter", "hostile", "resentful", "fed up", "unfair",
        "betrayed", "disgusted", "outraged", "stressed", "overwhelmed"
    );

    private static final List<String> FEAR_WORDS = Arrays.asList(
        "scared", "afraid", "anxious", "worried", "nervous", "panic", "terrified",
        "dread", "uneasy", "threatened", "insecure", "uncertain", "doubt",
        "fear", "frightened", "tense", "helpless", "danger", "failing", "exam"
    );

    public Map<String, Integer> detect(String text) {
        String lower = text.toLowerCase();
        String[] words = lower.split("\\W+");
        Set<String> wordSet = new HashSet<>(Arrays.asList(words));

        Map<String, Integer> scores = new LinkedHashMap<>();
        scores.put("sadness", countMatches(wordSet, SAD_WORDS));
        scores.put("joy",     countMatches(wordSet, JOY_WORDS));
        scores.put("anger",   countMatches(wordSet, ANGER_WORDS));
        scores.put("fear",    countMatches(wordSet, FEAR_WORDS));
        return scores;
    }

    private int countMatches(Set<String> wordSet, List<String> keywords) {
        int count = 0;
        for (String kw : keywords) {
            if (wordSet.contains(kw)) count++;
        }
        return Math.min(count * 20, 100); // scale 0–100
    }
}