package org.synechron.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.synechron.service.IWordCounter;

import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class WordCounterControllerTest{
    @Mock
    private IWordCounter wordCounter;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        wordCounter = mock(IWordCounter.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new WordCounterController(wordCounter)).build();
    }

    @Test
    public void testAddWords() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/word-counter/add-words")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[\"flower\", \"flor\", \"blume\"]"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(wordCounter, times(1)).addWords("flower", "flor", "blume");
    }

    @Test
    public void testCountWord() throws Exception {
        when(wordCounter.countWord("flower")).thenReturn(3);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/word-counter/count-word/flower"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("3"));
    }
}
