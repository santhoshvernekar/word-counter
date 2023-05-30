package org.synechron.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.synechron.service.IWordCounter;
import org.synechron.service.WordCounter;
import org.synechron.service.WordCounterImpl;
import org.synechron.utils.Translator;

@Configuration
public class ApplicationConfig {
    @Bean
    public WordCounter wordCounter(){
        Translator translator = new Translator();
        return new WordCounter(translator);
    }

    @Bean
    public IWordCounter wordCounter(WordCounter wordCounter){
        return new WordCounterImpl(wordCounter);
    }
}
