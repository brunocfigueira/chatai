package integration.openchat.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import integration.openchat.api.AppConstants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeminiService implements IChatAIService {
    private final RestTemplate restTemplate;
    private ObjectMapper mapper;
    private HttpHeaders headers;

    public GeminiService() {
        this.restTemplate = new RestTemplate();
        this.mapper = new ObjectMapper();
        this.headers = new HttpHeaders();
    }
    @Override
    public String getResponse(String prompt) {
        try {
            var request = buildRequest(prompt);
            var response = restTemplate.postForObject(AppConstants.GEMINI_URL_ADDRESS, request, String.class);
            return getResponseContent(response);
        } catch (Exception ex) {
            return AppConstants.MSG_ERROR.concat(" Origin: ").concat(ex.getMessage());
        }
    }

    private HttpEntity<String> buildRequest(String prompt) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(
                "{\n" +
                        "  \"contents\": [{\n" +
                        "    \"parts\":[{\"text\": \"" + prompt + "\"}]\n" +
                        "    }]\n" +
                        "   }",
                headers);
    }

    private String getResponseContent(String response) throws JsonProcessingException {
        var rootNode = mapper.readTree(response);
        return rootNode
                .path("candidates")
                .get(0)
                .path("content")
                .path("parts")
                .get(0)
                .path("text")
                .asText();
    }
}
