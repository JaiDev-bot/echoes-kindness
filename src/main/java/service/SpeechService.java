package service;


import com.microsoft.cognitiveservices.speech.ResultReason;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechRecognizer;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class SpeechService {

    private final SpeechConfig speechConfig;

    public SpeechService(SpeechConfig speechConfig) {
        this.speechConfig = speechConfig;
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
                System.out.println("👂 Ouvindo: " + e.getResult().getText());
            });

            // Evento disparado quando a pessoa que está falando termina
            recognizer.recognized.addEventListener((s, e) -> {
                if (e.getResult().getReason() == ResultReason.RecognizedSpeech) {
                    System.out.println(" Transcrição Final: " + e.getResult().getText());
                }
            });


            recognizer.startContinuousRecognitionAsync().get();

            // Mantém o programa rodando por 30 segundos para teste
            Thread.sleep(30000);


            recognizer.stopContinuousRecognitionAsync().get();
            System.out.println(" Sessão encerrada!!");
        }
    }
}


