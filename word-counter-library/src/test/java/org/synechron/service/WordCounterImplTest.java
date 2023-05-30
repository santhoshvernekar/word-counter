package org.synechron.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.synechron.exception.TranslationException;
import org.synechron.utils.Translator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

public class WordCounterImplTest {
    private WordCounter wordCounter;
    @Mock
    private IWordValidator wordValidator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        wordCounter = new WordCounter(wordValidator);
    }

    @Test
    public void testAddWords_WithValidWords() throws TranslationException {
        try (MockedStatic<Translator> mockedTranslator = mockStatic(Translator.class)) {
            mockedTranslator.when(() -> Translator.translate("flower")).thenReturn("flower");
            mockedTranslator.when(() -> Translator.translate("flor")).thenReturn("flower");
            mockedTranslator.when(() -> Translator.translate("blume")).thenReturn("flower");
            when(wordValidator.isValidWord("flower")).thenReturn(true);
            when(wordValidator.isValidWord("flor")).thenReturn(true);
            when(wordValidator.isValidWord("blume")).thenReturn(true);
            wordCounter.addWords("flower", "flor", "blume");
            assertEquals(3, wordCounter.countWord("flower"));
        }
    }

    @Test
    public void testAddWords_WithNonAlphabeticCharacters() throws TranslationException {
        wordCounter.addWords("flower1", "123", "!@#$");
        assertEquals(0, wordCounter.countWord("flower1"));
        assertEquals(0, wordCounter.countWord("123"));
        assertEquals(0, wordCounter.countWord("!@#$"));
    }

    @Test
    public void testAddWords_WithEmptyString() throws TranslationException {
        wordCounter.addWords("");
        assertEquals(0, wordCounter.countWord(""));
    }

    @Test
    public void testCountWord_WithNonExistingWord() throws TranslationException {
        assertEquals(0, wordCounter.countWord("flower"));
    }

    @Test
    public void testCountWord_WithExistingWord() throws TranslationException {
        try (MockedStatic<Translator> mockedTranslator = mockStatic(Translator.class)) {
            mockedTranslator.when(() -> Translator.translate("flower")).thenReturn("flower");
            when(wordValidator.isValidWord("flower")).thenReturn(true);
            wordCounter.addWords("flower", "flower", "flower");
            assertEquals(3, wordCounter.countWord("flower"));
        }
    }
}
