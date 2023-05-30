package org.synechron.service;

import org.synechron.exception.TranslationException;
import org.synechron.utils.Translator;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WordCounter implements IWordCounter {
    private final ConcurrentMap<String, Integer> wordCounts;
    private final IWordValidator wordValidator;
    private  Translator translator;
    public WordCounter(IWordValidator wordValidator, Translator translator) {
        this.wordValidator = wordValidator;
        wordCounts = new ConcurrentHashMap<>();
        this.translator = translator;
    }

    @Override
    public void addWords(String... words) throws TranslationException {
        for (String word : words) {
            if (isValidWord(word)) {
                try {
                    String translatedWord = this.translator.translate(word);
                    wordCounts.compute(translatedWord, (key, value) -> (value == null) ? 1 : value + 1);
                } catch (TranslationException e) {
                    // Handle the exception as needed
                    throw e;
                }
            }
        }
    }

    @Override
    public int countWord(String word) throws TranslationException {
        String translatedWord = this.translator.translate(word);
        return wordCounts.getOrDefault(translatedWord, 0);
    }

    private boolean isValidWord(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        String trimmedWord = word.trim();

        // Check if the trimmed word contains only alphabetic characters
        if (!trimmedWord.matches("[a-zA-Z]+")) {
            return false;
        }

        return true;
    }
}
