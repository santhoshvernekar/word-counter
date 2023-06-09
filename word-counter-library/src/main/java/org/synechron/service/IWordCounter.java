package org.synechron.service;

import org.synechron.exception.TranslationException;

public interface IWordCounter {
    /**
     * Adds one or more words to the word counter.
     *
     * @param words the words to add
     * @throws TranslationException if an error occurs during translation
     *
     */
    void addWords(String... words) throws TranslationException;

    /**
     * Returns the count of how many times a given word was added to the word counter.
     *
     * @param word the word to count
     * @return the count of the word
     * @throws TranslationException if an error occurs during translation
     *
     */
    int countWord(String word) throws TranslationException;
}