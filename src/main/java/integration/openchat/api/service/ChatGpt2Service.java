package integration.openchat.api.service;

import integration.openchat.api.AppConstants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatGpt2Service implements IChatAIService {
    private final RestTemplate restTemplate;
    private HttpHeaders headers;

    public ChatGpt2Service() {
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
    }

    @Override
    public String getResponse(String prompt) {
        try {
            var entity = buildEntity(prompt);
            var response = restTemplate.exchange(AppConstants.CHATGPT_URL_ADDRESS, HttpMethod.POST, entity, String.class);
            return response.getBody();
        } catch (Exception ex) {
            return AppConstants.MSG_ERROR.concat(" Origin: ").concat(ex.getMessage());
        }
    }

    private HttpEntity<Map<String, Object>> buildEntity(String prompt) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(AppConstants.CHATGPT_API_KEY);

        Map<String, Object> payload = new HashMap<>();
        payload.put("model", AppConstants.CHATGPT_MODEL);
        payload.put("prompt", prompt);
        payload.put("temperature", AppConstants.CHATGPT_TEMPERATURE);
        payload.put("max_tokens", AppConstants.CHATGPT_MAX_TOKENS);

        return new HttpEntity<>(payload, headers);
    }
}
