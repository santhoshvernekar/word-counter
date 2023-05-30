package org.synechron.controller;
import org.springframework.web.bind.annotation.*;
import org.synechron.service.IWordCounter;

@RestController
@RequestMapping("/api/v1/word-counter")
public class WordCounterController{

    private final IWordCounter wordCounter;

    public WordCounterController(IWordCounter wordCounter) {
        this.wordCounter = wordCounter;
    }

    @PostMapping("/add-words")
    public void addWords(@RequestBody String[] words) {
        this.wordCounter.addWords(words);
    }

    @GetMapping("/count-word/{word}")
    public int countWord(@PathVariable String word) {
        return this.wordCounter.countWord(word);
    }
}
