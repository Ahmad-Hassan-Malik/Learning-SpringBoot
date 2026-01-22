package com.ahmadmalik.mySpringBootProject.service;

import com.ahmadmalik.mySpringBootProject.api_Response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    private static final String apiKey = "6f6a5b54338a4a948a9110817262201";

    private static final String API = "https://api.worldweatheronline.com/premium/v1/weather.ashx?key=API-KEY&q=CITY&format=json";


    // RestTemplate acts like a web browser for your code to talk to external servers (APIs).
// It handles the HTTP connection (sending requests like GET/POST) and automatically
// converts the raw JSON response from the internet into your Java Objects.
    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        String finalAPI = API.replace("CITY", city).replace("API-KEY", apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET,null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }

}
