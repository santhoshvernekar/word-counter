package org.synechron.service;

import org.synechron.utils.Translator;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WordCounter implements IWordCounter {
    private final ConcurrentMap<String, Integer> wordCounts;
    private  Translator translator;
    public WordCounter(Translator translator) {
        wordCounts = new ConcurrentHashMap<>();
        this.translator = translator;
    }

    @Override
    public void addWords(String... words) {
        for (String word : words) {
            if (isValidWord(word)) {
                String translatedWord = this.translator.translate(word);
                wordCounts.compute(translatedWord, (key, value) -> (value == null) ? 1 : value + 1);
            }
        }
    }

    @Override
    public int countWord(String word) {
        String translatedWord = this.translator.translate(word);
        return wordCounts.getOrDefault(translatedWord, 0);
    }

    private boolean isValidWord(String word) {
        return word.matches("[a-zA-Z]+");
    }
}
