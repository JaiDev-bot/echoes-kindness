package jaiane.com.Echoes.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jaiane.com.Echoes.service.SpeechService;

@RestController
@RequestMapping("api/acessibilidade")
public class SpeechController {

    private final SpeechService speechService;

    public SpeechController(SpeechService speechService) {
        this.speechService = speechService;
    }

    @GetMapping("/ouvir")
    public String iniciarOuvinte() {

        new Thread(() -> {
            try {
                speechService.transcricaoEmTempoReal();
            } catch (Exception e) {
                System.err.println("Erro na transcrição: " + e.getMessage());
            }
        }).start();

        return "Microfone ativado! Acompanhe a transcrição no console do IntelliJ.";
    }
}


