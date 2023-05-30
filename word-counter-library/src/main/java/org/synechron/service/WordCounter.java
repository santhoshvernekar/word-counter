package org.synechron.service;

import org.synechron.exception.TranslationException;
import org.synechron.utils.Translator;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WordCounter implements IWordCounter {
    private final ConcurrentMap<String, Integer> wordCounts;
    private final IWordValidator wordValidator;

    public WordCounter(IWordValidator wordValidator) {
        this.wordValidator = wordValidator;
        wordCounts = new ConcurrentHashMap<>();
    }

    @Override
    public void addWords(String... words) throws TranslationException {
        for (String word : words) {
            if (this.wordValidator.isValidWord(word)) {
                try {
                    String translatedWord = Translator.translate(word);
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
        String translatedWord = Translator.translate(word);
        return wordCounts.getOrDefault(translatedWord, 0);
    }

}
