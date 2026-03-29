package com.mycompany.mc.engine;

import java.util.*;

public class ResponseGenerator {

    private static final List<String> CRISIS_WORDS = Arrays.asList(
        "kill myself", "end it all", "self harm", "hurt myself", "no point living",
        "don't want to live", "suicide", "giving up on life", "can't go on"
    );

    public Map<String, String> generate(String text, Map<String, Integer> emotions,
                                        int stress, int prevStress) {
        Map<String, String> result = new LinkedHashMap<>();

        // 1. Emotional Insight
        result.put("emotionalInsight", buildEmotionalInsight(emotions));

        // 2. Trend
        result.put("trend", buildTrend(stress, prevStress));

        // 3. Why
        result.put("why", buildWhy(text, emotions));

        // 4. Support Message
        result.put("supportMessage", buildSupportMessage(emotions, stress));

        // 5. Suggestions
        result.put("suggestions", buildSuggestions(emotions, stress));

        // 6. Safety Check
        String safety = buildSafetyNote(text);
        if (safety != null) result.put("supportNote", safety);

        return result;
    }

    private String buildEmotionalInsight(Map<String, Integer> e) {
        String dominant = e.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey).orElse("neutral");

        int dominantScore = e.getOrDefault(dominant, 0);
        if (dominantScore < 20) return "You seem to be in a fairly neutral space today — not too high, not too low.";

        return switch (dominant) {
            case "sadness" -> "You seem to be going through a heavy-hearted moment today. There's a quiet sadness in your words.";
            case "joy"     -> "You're carrying a warm, positive energy today — something good seems to be lifting your spirits!";
            case "anger"   -> "There's a sense of frustration or irritation running through how you're feeling today.";
            case "fear"    -> "You seem a little on edge today — there might be something weighing on your mind or worrying you.";
            default        -> "Your emotions seem mixed today, which is completely okay.";
        };
    }

    private String buildTrend(int stress, int prevStress) {
        int diff = stress - prevStress;
        if (diff > 10)       return "Increasing — your stress seems noticeably higher than yesterday.";
        else if (diff < -10) return "Decreasing — you seem to be handling things a bit better than before.";
        else                 return "Stable — your stress level is holding fairly steady compared to last time.";
    }

    private String buildWhy(String text, Map<String, Integer> e) {
        String lower = text.toLowerCase();
        StringBuilder why = new StringBuilder();

        if (lower.contains("exam") || lower.contains("deadline") || lower.contains("assignment"))
            why.append("Academic pressure seems to be a big factor. ");
        if (lower.contains("sleep") || lower.contains("tired") || lower.contains("exhausted"))
            why.append("A lack of rest can really amplify how hard things feel. ");
        if (lower.contains("friend") || lower.contains("family") || lower.contains("fight") || lower.contains("argument"))
            why.append("Tensions in your relationships may be adding to the weight. ");

        int sadness = e.getOrDefault("sadness", 0);
        int fear    = e.getOrDefault("fear",    0);
        int joy     = e.getOrDefault("joy",     0);

        if (sadness > 40) why.append("The sadness in your words suggests something meaningful hurt you. ");
        if (fear > 40)    why.append("There's some anxiety or fear present — perhaps about something uncertain ahead. ");
        if (joy > 40)     why.append("Despite everything, there's also some joy shining through. ");

        return why.length() == 0
            ? "Your overall tone and word choice suggest you're processing a mix of things today."
            : why.toString().trim();
    }

    private String buildSupportMessage(Map<String, Integer> e, int stress) {
        if (stress > 70)
            return "It sounds like today has been really tough — and that's okay to admit. You're not alone in feeling this way. Take it one small step at a time; you don't have to solve everything right now.";
        if (stress > 40)
            return "It seems like things are a bit heavy right now. That's understandable. Give yourself permission to feel it — and also to take a small breather.";
        return "You seem to be doing okay! It's great that you're checking in with yourself. Keep that self-awareness going.";
    }

    private String buildSuggestions(Map<String, Integer> e, int stress) {
        List<String> suggestions = new ArrayList<>();

        if (stress > 60) {
            suggestions.add("Try a 5-minute box breathing exercise (inhale 4s, hold 4s, exhale 4s, hold 4s) — it genuinely helps calm your nervous system.");
            suggestions.add("Step away from your screen for a short walk, even just 10 minutes outside can shift your mood.");
        } else if (e.getOrDefault("sadness", 0) > 40) {
            suggestions.add("Write down 3 small things you appreciated about today — they don't have to be big.");
            suggestions.add("Reach out to a friend or family member, even a simple text can help you feel more connected.");
        } else if (e.getOrDefault("anger", 0) > 40) {
            suggestions.add("Try journaling your frustrations freely — writing without filtering can help release tension.");
            suggestions.add("Do a brief physical activity like stretching or a quick jog to channel that energy.");
        } else {
            suggestions.add("Keep up with your journaling habit — you're doing great by reflecting on your day.");
            suggestions.add("Use this positive moment to plan something small you've been putting off.");
        }

        return "1. " + suggestions.get(0) + "\n2. " + (suggestions.size() > 1 ? suggestions.get(1) : "Consider talking to someone you trust about how you're feeling.");
    }

    private String buildSafetyNote(String text) {
        String lower = text.toLowerCase();
        for (String crisis : CRISIS_WORDS) {
            if (lower.contains(crisis)) {
                return "It sounds like you might be going through something really painful right now. Please know you don't have to carry this alone — consider reaching out to a trusted friend, family member, or a counselor. iCall India: 9152987821 | Vandrevala Foundation: 1860-2662-345 (24/7). You matter.";
            }
        }
        return null;
    }
}