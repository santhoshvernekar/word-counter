package org.synechron.service;

import org.synechron.exception.TranslationException;

public class WordCounterImpl implements IWordCounter{

    private final WordCounter wordCounter;

    public WordCounterImpl(WordCounter wordCounter){
        this.wordCounter = wordCounter;
    }
    /**
     * Adds one or more words to the word counter.
     *
     * @param words the words to add
     */
    @Override
    public void addWords(String... words) throws TranslationException {
        this.wordCounter.addWords(words);
    }

    /**
     * Returns the count of how many times a given word was added to the word counter.
     *
     * @param word the word to count
     * @return the count of the word
     */
    @Override
    public int countWord(String word) throws TranslationException {
        return this.wordCounter.countWord(word);
    }

    private boolean isValidWord(String word) {
        return word.matches("[a-zA-Z]+");
    }

}
