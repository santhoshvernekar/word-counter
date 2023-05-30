package org.synechron.service;

public class WordValidator implements IWordValidator {
    @Override
    public boolean isValidWord(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        String trimmedWord = word.trim();

        // Check if the trimmed word contains only alphabetic characters
        return trimmedWord.matches("[a-zA-Z]+");
    }
}
