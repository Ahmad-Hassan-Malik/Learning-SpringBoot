package com.ahmadmalik.mySpringBootProject.service;

import com.ahmadmalik.mySpringBootProject.api_Response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    private static final String API = "https://api.worldweatheronline.com/premium/v1/weather.ashx?key=API-KEY&q=CITY&format=json";

    @Autowired
    private RedisService redisService;

    // RestTemplate acts like a web browser for your code to talk to external servers (APIs).
// It handles the HTTP connection (sending requests like GET/POST) and automatically
// converts the raw JSON response from the internet into your Java Objects.
    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if (weatherResponse != null) {
            System.out.println("Weather got from redis...!!!");
            return weatherResponse;
        } else {
            String finalAPI = API.replace("CITY", city).replace("API-KEY", apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET,null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if (body != null) {
                redisService.set("weather_of_" + city, body, 300l);
            }
            return body;
        }

    }

}
