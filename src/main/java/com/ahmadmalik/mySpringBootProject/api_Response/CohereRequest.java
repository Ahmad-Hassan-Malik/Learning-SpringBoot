package com.ahmadmalik.mySpringBootProject.api_Response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CohereRequest {

    @JsonProperty("message")
    private String message;
}
