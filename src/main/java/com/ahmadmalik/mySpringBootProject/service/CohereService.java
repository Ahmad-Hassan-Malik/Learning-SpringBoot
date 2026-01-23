package com.ahmadmalik.mySpringBootProject.service;

import com.ahmadmalik.mySpringBootProject.api_Response.CohereRequest;
import com.ahmadmalik.mySpringBootProject.api_Response.CohereResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CohereService {

    //   this will show error because of static and final
    //  static ==  as static variable belongs to class not the object, spring does not handle class. it manages instances(beans)
    //  final  ==  when we write final with a variable, we need to assign value immediately. spring assigns value later that is why we cannot unse final
    //   same for other also

//    @Value("${cohere.api.key}")
//    private static final String cohereApiKey;

    @Value("${cohere.api.key}")
    private String cohereApiKey;

    // do not need to put api-key in the following url. gpt requires the key to be hidden inside the header
    private static final String API_URL = "https://api.cohere.ai/v1/chat";

    @Autowired
    private RestTemplate restTemplate;

    public String getCohereReply(String userPrompt) {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + cohereApiKey);
            headers.set("Content-Type", "application/json");

            CohereRequest request = new CohereRequest(userPrompt);

            HttpEntity<CohereRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<CohereResponse> apiResponse = restTemplate.exchange(API_URL, HttpMethod.POST, entity, CohereResponse.class);

            CohereResponse cohereResponse = apiResponse.getBody();
            if (cohereResponse != null && !cohereResponse.getText().isEmpty()) {
                return cohereResponse.getText();
            }
        } catch (Exception e) {
            System.out.println("Exception in Cohere service" + e);
        }

        return "No Response from Cohere";
    }
}
