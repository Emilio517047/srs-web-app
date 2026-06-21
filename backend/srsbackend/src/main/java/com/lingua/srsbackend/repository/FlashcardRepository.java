package com.lingua.srsbackend.repository;

import com.lingua.srsbackend.model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
    // JpaRepository automatically gives you save(), findAll(), findById(), and deleteById()!
}
