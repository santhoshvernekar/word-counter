package org.synechron.service;

public class WordValidator implements IWordValidator {
    @Override
    public boolean isValidWord(String word) {
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
