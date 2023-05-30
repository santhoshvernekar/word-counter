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
        Translator translator = new Translator();
        return new WordCounter(wordValidator, translator);
    }

    @Bean
    public IWordCounter wordCounter(WordCounter wordCounter){
        return new WordCounterImpl(wordCounter);
    }

}
