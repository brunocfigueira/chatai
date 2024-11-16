package integration.openchat.api;

import integration.openchat.api.service.IChatAIService;

import java.util.Scanner;

/**
 * Classe auxiliar para gerenciar interações com o terminal e exibição de opções de menu.
 */
public abstract class TerminalHelper {
    private Scanner scanner;
    protected String scannerValue;
    protected boolean hasWarning;
    protected String[] arrayMenuOptions;

    /**
     * Inicializa o ambiente do terminal.
     */
    protected void boot() {
        bootConfiguration();
    }

    /**
     * Configura o terminal com as definições padrão.
     */
    private void bootConfiguration() {
        setWarningDefault();
        clearTerminal(10);
        setScannerValueDefault();
        setScanner();
        setArrayMenuOptions(AppConstants.MENU_OPTIONS);
    }

    /**
     * Finaliza os recursos do terminal e exibe uma mensagem de encerramento.
     */
    protected void finish() {
        scanner.close();
        clearTerminal(10);
        displayTerminal(AppConstants.MSG_FINISH_SYS);
        clearTerminal(2);
    }

    /**
     * Define as opções de menu a serem exibidas.
     *
     * @param arrayValues Array de strings com as opções de menu.
     */
    protected void setArrayMenuOptions(String[] arrayValues) {
        arrayMenuOptions = arrayValues;
    }

    /**
     * Define o estado de aviso padrão como falso.
     */
    private void setWarningDefault() {
        hasWarning = false;
    }

    /**
     * Simula a limpeza do terminal imprimindo linhas em branco.
     *
     * @param lines Número de linhas em branco a serem impressas.
     */
    private void clearTerminal(int lines) {
        for (int i = 0; i < lines; i++) {
            System.out.println();
        }
        System.out.flush();
    }

    /**
     * Define o valor padrão do scanner.
     */
    private void setScannerValueDefault() {
        scannerValue = String.valueOf(AppConstants.KEEP_MENU);
    }

    /**
     * Inicializa o scanner para leitura de entrada do usuário.
     */
    private void setScanner() {
        scanner = new Scanner(System.in);
    }

    /**
     * Exibe uma pergunta ao usuário e armazena a entrada fornecida.
     *
     * @param prompt A pergunta a ser exibida no terminal.
     */
    protected void doAskingTerminal(String prompt) {
        System.out.println(prompt);
        System.out.flush();
        scannerValue = scanner.nextLine();
    }

    /**
     * Exibe um prompt perguntando ao usuário se deseja sair do sistema.
     *
     * @return Código de saída caso o usuário escolha sair, ou código para manter o menu.
     */
    protected int displayPromptExitSystem() {
        doAskingTerminal(AppConstants.ASK_SYSTEM_EXIT);
        if (scannerValue.equalsIgnoreCase("S") || scannerValue.toUpperCase().contains("SIM")) {
            return AppConstants.EXIT_MENU;
        }
        return AppConstants.KEEP_MENU;
    }

    /**
     * Obtém a opção de menu escolhida pelo usuário.
     *
     * @return A opção de menu escolhida ou 0 se a entrada for inválida.
     */
    protected int retrieveChosenMenu() {
        int opt = 0;
        try {
            opt = stringToInt(scannerValue);
        } catch (NumberFormatException ex) {
            hasWarning = true;
        }
        return opt;
    }

    /**
     * Converte uma string para um inteiro.
     *
     * @param value A string a ser convertida.
     * @return O valor inteiro correspondente à string.
     */
    private int stringToInt(String value) {
        return Integer.parseInt(value);
    }

    /**
     * Exibe uma string no terminal.
     *
     * @param value A string a ser exibida.
     */
    protected void displayTerminal(String value) {
        System.out.println(value.isEmpty() ? "" : value);
    }

    /**
     * Exibe as opções do menu e solicita que o usuário faça uma seleção.
     */
    protected void displayMenuOptions() {
        buildMenuHeader();
        buildMenuOptions();
        buildMenuFooter();
        doAskingTerminal(hasWarning ? AppConstants.MSG_WAR_MENU : AppConstants.ASK_MENU_OPTION);
        setWarningDefault();
    }

    /**
     * Constrói e exibe as opções do menu.
     */
    private void buildMenuOptions() {
        int optionNumber = 1;
        for (String option : arrayMenuOptions) {
            displayTerminal(optionNumber + ". " + option);
            optionNumber++;
        }
        displayTerminal("0. SAIR");
    }

    /**
     * Constrói e exibe o cabeçalho do menu.
     */
    private void buildMenuHeader() {
        displayTerminal("********************************");
        displayTerminal("**      MENU DE OPÇÕES        **");
        displayTerminal("********************************");
        displayTerminal("");
    }

    /**
     * Constrói e exibe o rodapé do menu.
     */
    private void buildMenuFooter() {
        displayTerminal("");
        displayTerminal("********************************");
    }

    /**
     * Coleta dados do usuário para tradução e exibe a resposta do serviço de IA.
     *
     * @param service O serviço de IA usado para realizar traduções.
     */
    protected void showTranslationQuestions(IChatAIService service) {
        doAskingTerminal("Informe idioma de origem:");
        var langOrigin = scannerValue;
        doAskingTerminal("Informe idioma de destino:");
        var langDestination = scannerValue;
        doAskingTerminal("Informe o texto para tradução:");
        var text = scannerValue;
        var prompt = buildTranslatePrompt(langOrigin, langDestination, text);
        displayTranslate(service, prompt, text);
    }

    protected void showOpenQuestions(IChatAIService service) {
        doAskingTerminal("Escreva aqui sua pergunta:");
        displayOpenResponses(service, scannerValue);
    }

    /**
     * Constrói o prompt de tradução para o serviço de IA.
     *
     * @param langOrigin      Idioma de origem.
     * @param langDestination Idioma de destino.
     * @param text            Texto a ser traduzido.
     * @return O prompt formatado para o serviço de IA.
     */
    private String buildTranslatePrompt(String langOrigin, String langDestination, String text) {
        return String.format("Traduza o texto de %s para %s. Texto: '%s'", langOrigin, langDestination, text);
    }

    /**
     * Exibe a resposta da tradução do serviço de IA.
     *
     * @param service    O serviço de IA para traduções.
     * @param prompt     O prompt de tradução.
     * @param textOrigin O texto original.
     */
    private void displayTranslate(IChatAIService service, String prompt, String textOrigin) {
        var response = service.getResponse(prompt);
        displayTerminal("---");
        displayTerminal("ChatAI:");
        displayTerminal("'" + response + "'");
        displayTerminal("");
    }

    private void displayOpenResponses(IChatAIService service, String prompt) {
        var response = service.getResponse(prompt);
        displayTerminal("---");
        displayTerminal("ChatAI:");
        displayTerminal("'" + response + "'");
        displayTerminal("");
    }

    /**
     * Método abstrato para ser implementado, contendo a lógica de execução do terminal.
     */
    public abstract void runTerminalConsole();
}