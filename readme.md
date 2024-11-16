# Aplicação Java Spring Boot - Integração com APIs de IA

Este projeto é uma aplicação Java 17 utilizando o framework Spring Boot que integra com os serviços de inteligência artificial ChatGPT e Gemini. Ele oferece um terminal interativo para interagir com os serviços, realizar traduções e gerar respostas.

---

## Autor

Desenvolvido por **Bruno Cesar Figueira**  
[LinkedIn - Bruno Figueira](https://www.linkedin.com/in/bruno-figueira/)

---

## Requisitos

Certifique-se de que você possui os seguintes requisitos instalados antes de iniciar:

- **Java 17** ou superior
- **Maven 3.8.1** ou superior
- **Git** para clonar o repositório

---

## Passo a Passo para Instalação

1. **Clone o repositório do projeto:**
   ```bash
   git clone https://github.com/seu-usuario/seu-projeto.git
   cd seu-projeto
   ```

2. **Configure a chave de API:**
    - Edite o arquivo `AppConstants.java` localizado em `src/main/java/integration/openchat/api`.
    - Substitua os valores de `CHATGPT_API_KEY` e `GEMINI_API_KEY` pela sua chave de API:
      ```java
      public static final String CHATGPT_API_KEY = "YOUR_API_KEY";
      public static final String GEMINI_API_KEY = "YOUR_API_KEY";
      ```

3. **Compile e construa o projeto:**
   Execute o comando abaixo para baixar as dependências e compilar o projeto:
   ```bash
   mvn clean install
   ```

4. **Inicie a aplicação:**
   Use o comando a seguir para executar o projeto:
   ```bash
   mvn spring-boot:run
   ```

5. **Interaja com o terminal:**
   Após iniciar a aplicação, você verá um menu no terminal com várias opções para interagir com os serviços de IA. Escolha as opções digitando os números correspondentes.

---

## Configuração Adicional

### Variáveis de Ambiente
Se preferir, você pode configurar as chaves de API usando variáveis de ambiente ao invés de hardcoding:
- No sistema operacional, configure:
  ```bash
  export CHATGPT_API_KEY=YOUR_API_KEY
  export GEMINI_API_KEY=YOUR_API_KEY
  ```
- Atualize o código para buscar as variáveis de ambiente:
  ```java
  public static final String CHATGPT_API_KEY = System.getenv("CHATGPT_API_KEY");
  public static final String GEMINI_API_KEY = System.getenv("GEMINI_API_KEY");
  ```

---

## Estrutura do Projeto

- **`AppConstants`**: Define as constantes do sistema, como chaves de API, URLs e mensagens do menu.
- **`MainApplication`**: Classe principal da aplicação que implementa a lógica do terminal.
- **Serviços de IA (`ChatGptService`, `ChatGpt2Service`, `GeminiService`)**: Gerenciam a comunicação com as APIs de IA.

---

## Contribuição

Se você deseja contribuir para este projeto:
1. Crie um fork do repositório.
2. Crie um branch para suas alterações:
   ```bash
   git checkout -b minha-feature
   ```
3. Envie um pull request quando sua funcionalidade estiver pronta.

---

## Licença

Este projeto é distribuído sob a licença MIT. Consulte o arquivo `LICENSE` para mais informações.

--- 

Para dúvidas ou sugestões, entre em contato com o autor via LinkedIn. 🎉