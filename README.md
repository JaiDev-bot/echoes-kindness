# 🎙️ Echoes of Kindness

**Echoes of Kindness** é uma solução de acessibilidade em tempo real desenvolvida para transformar a comunicação em ambientes de saúde. O projeto utiliza Inteligência Artificial para transcrever a fala de profissionais de saúde instantaneamente, exibindo-a em uma interface de alto contraste projetada para pacientes com deficiência auditiva, idosos ou pessoas com baixa visão.



---

## 📺 Demonstração em vídeo
> [!IMPORTANT]
> Clique no link abaixo para visualizar a transcrição em tempo real integrada ao sistema.
>
> 🎥 **[ASSISTA AO VÍDEO DO PROJETO AQUI]** (https://youtu.be/zX9IBxTWcRw)

---

##  Tecnologias e Stack
* **Java 21**: Versão de longo suporte para garantir robustez e performance.
* **Spring Boot 3.5.9**: Framework base para a construção da API escalável.
* **Azure Cognitive Services (Speech-to-Text)**: Motor de IA para transcrição de áudio em tempo real.
* **Azure Cosmos DB**: Banco de dados NoSQL escalável para persistência dos atendimentos.
* **Thymeleaf**: Engine de templates para a interface de acessibilidade.

## 🛠️ Diferenciais técnicos

### 1. Transcrição assíncrona e baixa Latência
O sistema utiliza o SDK da Microsoft para realizar o reconhecimento contínuo de fala. Através do evento `recognizing`, as palavras aparecem na tela enquanto o usuário fala, garantindo uma comunicação fluida e sem pausas dramáticas.

### 2. UX de acessibilidade
A interface `acessibilidade.html` foi desenvolvida com foco total na legibilidade:
* **Fundo Preto / Letras Amarelas**: Máximo contraste para baixa visão.
* **Tipografia gigante (`8vw`)**: Facilita a leitura à distância.
* **Auto-Scroll**: Lógica em JavaScript para manter as frases recentes sempre visíveis.

### 3. Integração cloud nativa
* **Localização**: Recurso hospedado em `Brazil South` para mínima latência.
* **Persistência automática**: Ao concluir uma frase (`recognized`), os dados são salvos no **Azure Cosmos DB**.

  ![Cosmo DB](https://github.com/JaiDev-bot/echoes-kindness/blob/main/cosmosVoz.png)

---


<details>
  <summary> 🎲 Por que Cosmo DB?</summary>

  > Não guardamos os atendimentos no Cosmos DB apenas por ser um banco NoSQL escalável de classe mundial. Existe uma camada de proteção estratégica aqui:

### 1. Valor probatório e auditoria judicial 📜
Em um ambiente hospitalar, o que não é registrado, não aconteceu. 
* **Integridade dos dados:** O Cosmos DB oferece garantias de consistência que asseguram que o registro da consulta não foi alterado indevidamente.
* **Timestamp inviolável:** Cada atendimento é gravado com um carimbo de tempo preciso. Em caso de processos judiciais ou auditorias de conformidade (como a LGPD), temos uma trilha de auditoria clara de quem falou o quê e quando.
* **Disponibilidade 99.999%:** Se um juiz pedir os dados, o sistema não pode estar "fora do ar". A replicação global da Azure garante que a prova esteja sempre acessível.

### 2. Governança e LGPD por design 🔐
* **Criptografia em Repouso:** Todos os dados no Cosmos DB são criptografados por padrão, atendendo às normas mais rigorosas de proteção de dados sensíveis de saúde.
* **Isolamento de Dados:** Através das Partition Keys (`tipoAtendimento`), garantimos que os dados sejam organizados de forma lógica e segura, facilitando o expurgo de dados conforme o direito ao esquecimento previsto na lei.
  
</details>

<details> 
 <summary>🧩 A Lógica dos Fragmentos: Por que o texto quebra no Cosmos DB? </summary>

> O nosso container no **Azure Cosmos DB**, vai notar que as transcrições aparecem em fragmentos. Isso não é um bug, é a implementação de um fluxo de **Reconhecimento Contínuo e Assíncrono**.

### 1. O Conceito de "Unidade de Pensamento" (Utterance)
A IA da Azure não espera o médico ditar um parágrafo inteiro. Ela trabalha com **Utterances** (enunciados). 
* Sempre que o sistema detecta uma pausa natural, uma queda de entonação ou o fim de uma sentença, o evento `recognized` é disparado.
* Cada disparo gera um novo documento no Cosmos DB. 

### 2. Vantagens Estratégicas da Fragmentação
* **Segurança Jurídica:** Em vez de um blocão de texto que poderia ser editado, temos "logs" granulares de cada frase dita, com seu próprio carimbo de tempo (*timestamp*). Isso cria uma linha do tempo incontestável da consulta.
* **Performance de Interface:** É essa quebra que permite que o paciente leia a frase anterior enquanto a próxima já está sendo processada.
* **Análise de Sentimento Granular:** Ter as frases fragmentadas permite que, no futuro, possamos rodar IAs de análise de sentimento para identificar exatamente em qual momento da consulta o paciente ficou mais ansioso ou o médico foi mais enfático.

### 3. Integridade e Reconstrução
Graças à nossa estrutura de dados no modelo `Atendimento`, cada fragmento carrega o `id` e o contexto necessário para que, se precisarmos, possamos reconstruir o diálogo completo apenas ordenando os registros pelo tempo.

</details>


<details>
<summary> 🔊 Por que Azure Speech Services? </summary>

> Segurança em saúde também é sobre manter o profissionalismo. O **Azure Speech Services** possui camadas de inteligência que protegem a transcrição:
> 
* **Content Moderation:** A IA da Azure pode ser configurada para identificar e mascarar palavrões ou conteúdos impróprios automaticamente durante a transcrição.
* **Proteção de Marca e Ética:** Isso evita que termos ofensivos ou erros de interpretação chulos sejam imortalizados no prontuário do paciente, protegendo a imagem da instituição e do médico.

![print](https://github.com/JaiDev-bot/echoes-kindness/blob/main/palavr%C3%B5es.png)
  
</details>

## 📁 Estrutura do projeto
* `src/main/java/jaiane/com/Echoes/controller/`: Rotas de ativação e tela.
* `src/main/java/jaiane/com/Echoes/service/`: Lógica de integração com Azure Speech.
* `src/main/java/jaiane/com/Echoes/config/`: Configurações de Cloud.
* `src/main/resources/templates/`: Interface de acessibilidade (HTML/CSS/JS).

## 🔧 Como executar
1. Configure suas chaves no `application.properties`:
   ```properties
   ai.speech.key=SUA_CHAVE_AQUI
   ai.speech.region=brazilsouth
