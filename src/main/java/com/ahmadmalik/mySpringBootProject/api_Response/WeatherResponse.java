package com.ahmadmalik.mySpringBootProject.api_Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
//public class WeatherResponse {
//
//    private CurrentCondition currentCondition;
//
//    @Getter
//    @Setter
//    public static class CurrentCondition {
//        private String temp_C;
//        private String FeelsLikeC;
//        private String humidity;
//        private String windspeedKmph;
//    }
//
//}

import java.util.List;

@Getter
@Setter
public class WeatherResponse {

    private Data data;

    @Getter
    @Setter
    public static class Data {
        // private List<CurrentCondition> current_condition;

        @JsonProperty("current_condition")
        private List<CurrentCondition> currentCondition;
    }

    @Getter
    @Setter
    public static class CurrentCondition {

        //private String temp_C;
        @JsonProperty("temp_C")
        private String tempC;

        private String FeelsLikeC;
        private String humidity;
        private String windspeedKmph;
    }
}



