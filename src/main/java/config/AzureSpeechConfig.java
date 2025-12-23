package config;


import com.microsoft.cognitiveservices.speech.SpeechConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureSpeechConfig {

    @Value("${ai.speech.key}")
    private String speechKey;

    @Value("${ai.speech.region}")
    private String speechRegion;

    @Bean
    public SpeechConfig speechConfig() {

        SpeechConfig config = SpeechConfig.fromSubscription(speechKey, speechRegion);

        config.setSpeechRecognitionLanguage("pt-BR");

        return config;
    }
}
