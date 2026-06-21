package com.lingua.srsbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.lingua.srsbackend.model.Flashcard;
import com.lingua.srsbackend.repository.FlashcardRepository;

@RestController
@RequestMapping("/api/flashcards")
@CrossOrigin(origins = "*") // Allows your frontend to talk to this backend safely
public class FlashcardController {
    
    @Autowired
    FlashcardRepository flashcardRepository;

    // 1. GET ALL FLASHCARDS
    @GetMapping
    public List<Flashcard> getAllFlashcards() {
        return flashcardRepository.findAll();
    }

    // 2. CREATE A NEW FLASHCARD (POST)
    @PostMapping
    public Flashcard createFlashcard(@RequestBody Flashcard flashcard) {
        return flashcardRepository.save(flashcard);
    }

    // 3. UPDATE AN EXISTING FLASHCARD (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Flashcard> updateFlashcard(@PathVariable Long id, @RequestBody Flashcard cardDetails) {
        Flashcard flashcard = flashcardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flashcard not found with id: " + id));

        // For now, this saves the updated card details
        return ResponseEntity.ok(flashcardRepository.save(cardDetails));
    }
        
    // 4. DELETE A FLASHCARD
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFlashcard(@PathVariable Long id) {
        flashcardRepository.deleteById(id);
        return ResponseEntity.ok("Flashcard deleted successfully.");
    }
}
