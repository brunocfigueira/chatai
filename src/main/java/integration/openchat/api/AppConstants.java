package integration.openchat.api;

/**
 * Classe de constantes globais da aplicação.
 * Contém configurações de API, mensagens do sistema e valores para controle do menu.
 *
 * <p>
 * Esta classe não pode ser instanciada.
 * </p>
 */
public final class AppConstants {

    /**
     * Chave de API para autenticação com o serviço ChatGPT.
     */
    public static final String CHATGPT_API_KEY = "YOUR_API_KEY";

    /**
     * URL do endpoint da API do ChatGPT.
     */
    public static final String CHATGPT_URL_ADDRESS = "https://api.openai.com/v1/chat/completions";

    /**
     * Modelo do ChatGPT utilizado para requisições.
     */
    public static final String CHATGPT_MODEL = "gpt-3.5-turbo";

    /**
     * Temperatura utilizada nas requisições do ChatGPT.
     * Define a criatividade das respostas geradas.
     */
    public static final double CHATGPT_TEMPERATURE = 0.7;

    /**
     * Número máximo de tokens permitidos por requisição ao ChatGPT.
     */
    public static final int CHATGPT_MAX_TOKENS = 100;

    /**
     * Chave de API para autenticação com o serviço Gemini.
     */
    public static final String GEMINI_API_KEY = "YOUR_API_KEY";

    /**
     * URL do endpoint da API do Gemini.
     */
    public static final String GEMINI_URL_ADDRESS = "https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent?key=" + GEMINI_API_KEY;

    /**
     * Opções do menu principal da aplicação.
     * Adicione aqui novas opções de menu
     */
    public static final String[] MENU_OPTIONS = new String[]{
            "Perguntar (ChatGPT)",
            "Tradutor (ChatGPT)",
            "Tradutor (Chat2GPT)",
            "Perguntar (Gemini)",
            "Tradutor (Gemini)",
    };

    /**
     * Valor constante para manter o menu ativo.
     */
    public static final int KEEP_MENU = 1;

    /**
     * Valor constante para finalizar o menu.
     */
    public static final int EXIT_MENU = -1;

    /**
     * Mensagem de solicitação para escolher uma opção do menu.
     */
    public static final String ASK_MENU_OPTION = "Escolha um número da opção desejada:";

    /**
     * Mensagem de confirmação para sair do sistema.
     */
    public static final String ASK_SYSTEM_EXIT = "Deseja realmente sair do sistema? (S/N) Sim / Não";

    /**
     * Mensagem exibida ao finalizar a aplicação com sucesso.
     */
    public static final String MSG_FINISH_SYS = "Aplicação finalizada com sucesso.";

    /**
     * Mensagem de aviso ao selecionar uma opção inválida no menu.
     */
    public static final String MSG_WAR_MENU = "- Opção incorreta. Informe um número correspondente ao menu de opções:";

    /**
     * Mensagem de erro genérica exibida durante a execução de falhas inesperadas.
     */
    public static final String MSG_ERROR = "Ocorreu um erro inesperado durante a execução.";

    /**
     * Construtor privado para impedir a instanciação da classe.
     *
     * @throws UnsupportedOperationException Sempre que chamado.
     */
    private AppConstants() {
        throw new UnsupportedOperationException("Esta classe não pode ser instanciada");
    }
}