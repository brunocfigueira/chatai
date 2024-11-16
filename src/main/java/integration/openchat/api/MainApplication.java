package integration.openchat.api;

import integration.openchat.api.service.ChatGpt2Service;
import integration.openchat.api.service.ChatGptService;
import integration.openchat.api.service.GeminiService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação, que implementa a lógica do terminal e integra serviços de IA.
 * Extende {@link TerminalHelper} para lidar com interações no terminal
 * e implementa {@link CommandLineRunner} para execução de tarefas na inicialização.
 */
@SpringBootApplication
public class MainApplication extends TerminalHelper implements CommandLineRunner {
    private final ChatGptService chatGptService;
    private final ChatGpt2Service chatGpt2Service;
    private final GeminiService geminiService;

    /**
     * Construtor da classe que inicializa os serviços de IA.
     */
    public MainApplication() {
        this.chatGptService = new ChatGptService();
        this.chatGpt2Service = new ChatGpt2Service();
        this.geminiService = new GeminiService();
    }

    /**
     * Método principal da aplicação. Inicializa o contexto Spring Boot.
     *
     * @param args Argumentos de linha de comando.
     */
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    /**
     * Método chamado automaticamente após a inicialização do contexto Spring.
     * Executa a lógica principal do terminal.
     *
     * @param args Argumentos passados pela linha de comando.
     */
    @Override
    public void run(String... args) {
        runTerminalConsole();
    }

    /**
     * Método que coordena a execução do terminal, incluindo inicialização,
     * exibição de menus e finalização.
     */
    @Override
    public void runTerminalConsole() {
        boot();
        showScreen();
        finish();
    }

    /**
     * Exibe a tela principal do terminal e gerencia as interações com o usuário.
     * Permite selecionar opções de menu e interagir com os serviços de IA.
     */
    private void showScreen() {
        var optionChosen = AppConstants.KEEP_MENU;
        while (optionChosen > AppConstants.EXIT_MENU) {

            // Exibe as opções do menu e captura a escolha do usuário
            displayMenuOptions();
            optionChosen = retrieveChosenMenu();

            // Executa ações com base na opção escolhida
            switch (optionChosen) {
                case 0 -> optionChosen = displayPromptExitSystem();
                case 1 ->
                    // Interage com o serviço ChatGPT
                        showOpenQuestions(chatGptService);
                case 2 ->
                    // Interage com o serviço ChatGPT
                        showTranslationQuestions(chatGptService);
                case 3 ->
                    // Interage com o serviço ChatGPT2
                        showTranslationQuestions(chatGpt2Service);
                case 4 ->
                    // Interage com o serviço Gemini
                        showOpenQuestions(geminiService);
                case 5 ->
                    // Interage com o serviço Gemini
                        showTranslationQuestions(geminiService);
                default ->
                    // Define um aviso caso a opção seja inválida
                        hasWarning = true;
            }
        }
    }
}