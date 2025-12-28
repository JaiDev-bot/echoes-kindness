package jaiane.com.Echoes.service;


import com.microsoft.cognitiveservices.speech.ResultReason;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechRecognizer;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;
import jaiane.com.Echoes.model.Atendimento;
import jaiane.com.Echoes.repository.SpeechRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class SpeechService {

    private final SpeechRepository speechRepository;
    private static String ultimaFrase = "Aguardando fala do médico...";
    private final SpeechConfig speechConfig;

    public SpeechService(SpeechConfig speechConfig, SpeechRepository speechRepository) {
        this.speechConfig = speechConfig;
        this.speechRepository = speechRepository;
    }

    //Com o Assincronismo, o texto vai aparecendo palavra por palavra enquanto o médico fala, se fosse um
    // código sincrono, o médico falaria e teria uma pausa dramatica de 10 segundos, enquanto o paciente teria na tela
    // a mensagem 10 segundos depois (não tem um fluxo limpo e fluido)


    public void transcricaoEmTempoReal() throws InterruptedException, ExecutionException {
        // O AudioConfig.fromDefaultMicrophoneInput() acessa o hardware no PC
        try (AudioConfig audioConfig = AudioConfig.fromDefaultMicrophoneInput();
             SpeechRecognizer recognizer = new SpeechRecognizer(speechConfig, audioConfig)) {

            System.out.println("🎙 [Acessibilidade] Microfone aberto. Pode falar...");

            // Evento disparado enquanto o usuario ainda está falando, no caso do tablet de um paciente, enquanto o médico fala
            // o paciente pode ver em tempo real as palavras se formando
            recognizer.recognizing.addEventListener((s, e) -> {
                String parcial = e.getResult().getText();
                System.out.println("👂 Ouvindo: " + e.getResult().getText());

                ultimaFrase = parcial;
            });

            // Evento disparado quando a pessoa que está falando termina
            recognizer.recognized.addEventListener((s, e) -> {
                if (e.getResult().getReason() == ResultReason.RecognizedSpeech) {
                    String textoFinal = e.getResult().getText();
                    System.out.println(" Transcrição final: " + e.getResult().getText());
                    ultimaFrase = textoFinal;


                    Atendimento atendimento = new Atendimento();
                    atendimento.setTextoTranscrito(textoFinal);
                    atendimento.setConteudo("Consulta médica");
                    atendimento.setTimestamp(String.valueOf(System.currentTimeMillis()));

                    // salvando no Cosmo
                    speechRepository.save(atendimento);
                    System.out.println("Gravado no Cosmos DB: " + textoFinal);
                }

            });


            recognizer.startContinuousRecognitionAsync().get();

            // Mantém o programa rodando por 30 segundos para teste
            Thread.sleep(60000);



        }

    }

    public String getUltimaFrase() {
        return ultimaFrase ;
    }
}


