package com.ahmadmalik.mySpringBootProject.service;

import com.ahmadmalik.mySpringBootProject.api_Response.ChatgptRequest;
import com.ahmadmalik.mySpringBootProject.api_Response.ChatgptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatgptService {

    @Value("${chatGPT.api.key}")
    private String gptApiKey;

    // do not need to put api-key in the following url. gpt requires the key to be hidden inside the header
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    @Autowired
    private RestTemplate restTemplate;


    public String getChatgptReply(String userPrompt) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + gptApiKey);
        headers.set("Content-Type", "application/json");

        ChatgptRequest request = new ChatgptRequest();
        request.setModel("gpt-3.5-turbo");

        List<ChatgptRequest.Message> messages = new ArrayList<>();
        messages.add(new ChatgptRequest.Message("user", userPrompt));
        request.setMessages(messages);

        HttpEntity<ChatgptRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<ChatgptResponse> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, ChatgptResponse.class);
        ChatgptResponse gptResponse = response.getBody();
        if (gptResponse != null && !gptResponse.getChoices().isEmpty()) {
            return gptResponse.getChoices().get(0).getMessage().getContent();
        }


        return "No response from AI";
    }
}
