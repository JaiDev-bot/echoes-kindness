package config;


import com.microsoft.cognitiveservices.speech.SpeechConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureSpeechConfig {

    @Value("${ai.speech.key}")
    private String speechKey;


    // Não necessita de endpoin como o text-analyst, pois o audio precisa ser quebrado em pequenos pacotes em um tempo muito
    // mais rapido que a anlise de texto, o principal motivo é latência e rapidez, se seu recurso está no leste dos EUA,
    // vai demorar um pouco mais(o que para o Azure não é muito), o audio vai para lá, é processado e volta.Por isso é interessante
    // fazer seus recursos em regiões proximas. Fiz esse no Brazil South.
    @Value("${ai.speech.region}")
    private String speechRegion;


    // Garante que a instância seja configurada com o locale 'pt-BR' para acessibilidade.
    @Bean
    public SpeechConfig speechConfig() {

        SpeechConfig config = SpeechConfig.fromSubscription(speechKey, speechRegion);

        config.setSpeechRecognitionLanguage("pt-BR");

        return config;
    }
}
