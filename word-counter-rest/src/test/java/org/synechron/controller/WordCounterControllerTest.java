package org.synechron.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.synechron.exception.TranslationException;
import org.synechron.service.IWordCounter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class WordCounterControllerTest {
    private WordCounterController wordCounterController;

    @Mock
    private IWordCounter wordCounter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        wordCounterController = new WordCounterController(wordCounter);
    }

    @Test
    public void testCountWord_Success() throws TranslationException {
        String word = "flower";
        int count = 5;

        when(wordCounter.countWord(word)).thenReturn(count);

        ResponseEntity<Integer> response = wordCounterController.countWord(word);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Integer.valueOf(count), response.getBody());
    }

    @Test
    public void testCountWord_TranslationException() throws TranslationException {
        String word = "flower";

        when(wordCounter.countWord(word)).thenThrow(new TranslationException("Translation failed", new Exception()));

        ResponseEntity<Integer> response = wordCounterController.countWord(word);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(Integer.valueOf(0), response.getBody());
    }
}
