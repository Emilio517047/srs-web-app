package com.lingua.srsbackend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "flashcards")
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String frontText; // The foreign word/phrase

    @Column(nullable = false)
    private String backText;  // The translation

    private int reviewIntervalDays = 1; // Spaced repetition starts at 1 day

    private double easeFactor = 2.5;    // Standard SuperMemo SM-2 starting multiplier

    private LocalDateTime nextReviewAt = LocalDateTime.now(); // Scheduled for review immediately

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrontText() {
        return frontText;
    }

    public void setFrontText(String frontText) {
        this.frontText = frontText;
    }

    public String getBackText() {
        return backText;
    }

    public void setBackText(String backText) {
        this.backText = backText;
    }

    public int getReviewIntervalDays() {
        return reviewIntervalDays;
    }

    public void setReviewIntervalDays(int reviewIntervalDays) {
        this.reviewIntervalDays = reviewIntervalDays;
    }

    public double getEaseFactor() {
        return easeFactor;
    }

    public void setEaseFactor(double easeFactor) {
        this.easeFactor = easeFactor;
    }

    public LocalDateTime getNextReviewAt() {
        return nextReviewAt;
    }

    public void setNextReviewAt(LocalDateTime nextReviewAt) {
        this.nextReviewAt = nextReviewAt;
    }
}