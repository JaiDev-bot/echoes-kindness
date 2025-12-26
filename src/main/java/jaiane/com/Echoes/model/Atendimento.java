package jaiane.com.Echoes.model;


import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

import java.util.UUID;

@Container(containerName = "Atendimentos")
public class Atendimento {

    private String id = UUID.randomUUID().toString();

    @PartitionKey
    private String tipoAtendimento = "VOZ";

    private String textoTranscrito;
    private String timestamp;

    private String conteudo;
    private String dataHora;

    public Atendimento(){

    }

    public Atendimento(String id, String tipoAtendimento, String textoTranscrito, String timestamp, String conteudo, String dataHora) {
        this.id = id;
        this.tipoAtendimento = tipoAtendimento;
        this.textoTranscrito = textoTranscrito;
        this.timestamp = timestamp;
        this.conteudo = conteudo;
        this.dataHora=dataHora;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(String tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }

    public String getTextoTranscrito() {
        return textoTranscrito;
    }

    public void setTextoTranscrito(String textoTranscrito) {
        this.textoTranscrito = textoTranscrito;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}


