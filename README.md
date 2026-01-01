# 🎙️ Echoes of Kindness

**Echoes of Kindness** é uma solução de acessibilidade em tempo real desenvolvida para transformar a comunicação em ambientes de saúde. O projeto utiliza Inteligência Artificial para transcrever a fala de profissionais de saúde instantaneamente, exibindo-a em uma interface de alto contraste projetada para pacientes com deficiência auditiva, idosos ou pessoas com baixa visão.

---

## 📺 Demonstração em Vídeo
> [!IMPORTANT]
> Clique no link abaixo para visualizar a transcrição em tempo real integrada ao sistema.
>
> 🎥 **[ASSISTA AO VÍDEO DO PROJETO AQUI]** (https://youtu.be/zX9IBxTWcRw)

---

## 🚀 Tecnologias e Stack
* **Java 21**: Versão de longo suporte para garantir robustez e performance.
* **Spring Boot 3.5.9**: Framework base para a construção da API escalável.
* **Azure Cognitive Services (Speech-to-Text)**: Motor de IA para transcrição de áudio em tempo real.
* **Azure Cosmos DB**: Banco de dados NoSQL escalável para persistência dos atendimentos.
* **Thymeleaf**: Engine de templates para a interface de acessibilidade.

## 🛠️ Diferenciais Técnicos

### 1. Transcrição Assíncrona e Baixa Latência
O sistema utiliza o SDK da Microsoft para realizar o reconhecimento contínuo de fala. Através do evento `recognizing`, as palavras aparecem na tela enquanto o usuário fala, garantindo uma comunicação fluida e sem pausas dramáticas.

### 2. UX de Acessibilidade
A interface `acessibilidade.html` foi desenvolvida com foco total na legibilidade:
* **Fundo Preto / Letras Amarelas**: Máximo contraste para baixa visão.
* **Tipografia Gigante (`8vw`)**: Facilita a leitura à distância.
* **Auto-Scroll**: Lógica em JavaScript para manter as frases recentes sempre visíveis.

### 3. Integração Cloud Nativa
* **Localização**: Recurso hospedado em `Brazil South` para mínima latência.
* **Persistência Automática**: Ao concluir uma frase (`recognized`), os dados são salvos no **Azure Cosmos DB**.

  ![Cosmo DB](https://github.com/JaiDev-bot/echoes-kindness/blob/main/cosmosVoz.png)

## 📁 Estrutura do Projeto
* `src/main/java/jaiane/com/Echoes/controller/`: Rotas de ativação e tela.
* `src/main/java/jaiane/com/Echoes/service/`: Lógica de integração com Azure Speech.
* `src/main/java/jaiane/com/Echoes/config/`: Configurações de Cloud.
* `src/main/resources/templates/`: Interface de acessibilidade (HTML/CSS/JS).

## 🔧 Como Executar
1. Configure suas chaves no `application.properties`:
   ```properties
   ai.speech.key=SUA_CHAVE_AQUI
   ai.speech.region=brazilsouth
