package org.synechron.controller;
import org.springframework.web.bind.annotation.*;
import org.synechron.exception.TranslationException;
import org.synechron.service.IWordCounter;

@RestController
@RequestMapping("/api/v1/word-counter")
public class WordCounterController{

    private final IWordCounter wordCounter;

    public WordCounterController(IWordCounter wordCounter) {
        this.wordCounter = wordCounter;
    }

    @PostMapping("/add-words")
    public void addWords(@RequestBody String[] words) throws TranslationException {
        this.wordCounter.addWords(words);
    }

    @GetMapping("/count-word/{word}")
    public int countWord(@PathVariable String word) throws TranslationException {
        return this.wordCounter.countWord(word);
    }
}
