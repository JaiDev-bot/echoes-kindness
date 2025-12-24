package jaiane.com.Echoes.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import jaiane.com.Echoes.model.Atendimento;

public interface SpeechRepository extends CosmosRepository<Atendimento, String> {

}
