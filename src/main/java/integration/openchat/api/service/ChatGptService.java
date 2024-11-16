package integration.openchat.api.service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import integration.openchat.api.AppConstants;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatGptService implements IChatAIService {
    private OpenAiService service;

    public ChatGptService() {
        this.service = new OpenAiService(AppConstants.CHATGPT_API_KEY);
    }

    private ChatCompletionRequest buildChatRequest(String prompt) {
        return ChatCompletionRequest.builder()
                .model(AppConstants.CHATGPT_MODEL)
                .messages(List.of(
                        new ChatMessage("user", prompt)
                ))
                .temperature(AppConstants.CHATGPT_TEMPERATURE)
                .maxTokens(AppConstants.CHATGPT_MAX_TOKENS)
                .build();
    }

    public String getResponse(String prompt) {
        try {
            var request = buildChatRequest(prompt);
            return service.createChatCompletion(request)
                    .getChoices()
                    .get(0)
                    .getMessage()
                    .getContent();

        } catch (Exception ex) {
            return AppConstants.MSG_ERROR.concat(" Origin: ").concat(ex.getMessage());
        }
    }
}
