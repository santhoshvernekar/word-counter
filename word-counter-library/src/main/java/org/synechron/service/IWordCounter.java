package org.synechron.service;

public interface IWordCounter {
    /**
     * Adds one or more words to the word counter.
     *
     * @param words the words to add
     */
    void addWords(String... words);

    /**
     * Returns the count of how many times a given word was added to the word counter.
     *
     * @param word the word to count
     * @return the count of the word
     */
    int countWord(String word);
}