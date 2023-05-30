package org.synechron.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.synechron.service.*;
import org.synechron.utils.Translator;

@Configuration
public class ApplicationConfig {

    @Bean
    public WordCounter wordCounter(){
        IWordValidator wordValidator = new WordValidator();
        return new WordCounter(wordValidator);
    }
    @Bean
    public IWordCounter wordCounter(WordCounter wordCounter){
        return new WordCounterImpl(wordCounter);
    }

}
