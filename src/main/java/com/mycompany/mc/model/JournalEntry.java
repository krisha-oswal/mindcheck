package com.mycompany.mc.model;

public class JournalEntry {
    private String text;
    private int previousStressScore;

    public JournalEntry() {}

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public int getPreviousStressScore() { return previousStressScore; }
    public void setPreviousStressScore(int previousStressScore) {
        this.previousStressScore = previousStressScore;
    }
}