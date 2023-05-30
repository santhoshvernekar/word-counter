package org.synechron.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.synechron.exception.TranslationException;
import org.synechron.service.IWordCounter;

import java.util.List;

@RestController
@RequestMapping("/api/v1/word-counter")
public class WordCounterController{

    private final IWordCounter wordCounter;

    public WordCounterController(IWordCounter wordCounter) {
        this.wordCounter = wordCounter;
    }

    @PostMapping("/words")
    public ResponseEntity<String> addWords(@RequestBody List<String> words) {
        try {
            wordCounter.addWords(words.toArray(new String[0]));
            return ResponseEntity.ok("Words added successfully");
        } catch (TranslationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while adding words: " + e.getMessage());
        }
    }

    @GetMapping("/count/{word}")
    public ResponseEntity<Integer> countWord(@PathVariable String word) {
        try {
            int count = wordCounter.countWord(word);
            return ResponseEntity.ok(Integer.valueOf(count));
        } catch (TranslationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Integer.valueOf(0));
        }
    }
}
