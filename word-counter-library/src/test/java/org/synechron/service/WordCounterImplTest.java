package org.synechron.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.synechron.utils.Translator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

public class WordCounterImplTest {
    private WordCounter wordCounter;
    @Mock
    private Translator translator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        wordCounter = new WordCounter(translator);
    }

    @Test
    public void testAddWords_WithValidWords() {
        try (MockedStatic<Translator> mockedTranslator = mockStatic(Translator.class)) {
            mockedTranslator.when(() -> Translator.translate("flower")).thenReturn("flower");
            mockedTranslator.when(() -> Translator.translate("flor")).thenReturn("flower");
            mockedTranslator.when(() -> Translator.translate("blume")).thenReturn("flower");
            wordCounter.addWords("flower", "flor", "blume");
            assertEquals(3, wordCounter.countWord("flower"));
        }
    }

    @Test
    public void testAddWords_WithNonAlphabeticCharacters() {
        wordCounter.addWords("flower1", "123", "!@#$");
        assertEquals(0, wordCounter.countWord("flower1"));
        assertEquals(0, wordCounter.countWord("123"));
        assertEquals(0, wordCounter.countWord("!@#$"));
    }

    @Test
    public void testAddWords_WithEmptyString() {
        wordCounter.addWords("");
        assertEquals(0, wordCounter.countWord(""));
    }

    @Test
    public void testCountWord_WithNonExistingWord() {
        assertEquals(0, wordCounter.countWord("flower"));
    }

    @Test
    public void testCountWord_WithExistingWord() {
        try (MockedStatic<Translator> mockedTranslator = mockStatic(Translator.class)) {
            mockedTranslator.when(() -> Translator.translate("flower")).thenReturn("flower");
            wordCounter.addWords("flower", "flower", "flower");
            assertEquals(3, wordCounter.countWord("flower"));
        }
    }
}
