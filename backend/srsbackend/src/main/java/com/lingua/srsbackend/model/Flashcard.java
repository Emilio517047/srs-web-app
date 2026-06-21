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
}