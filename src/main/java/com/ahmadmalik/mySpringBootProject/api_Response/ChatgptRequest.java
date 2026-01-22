package com.ahmadmalik.mySpringBootProject.api_Response;

import lombok.*;
import org.apache.catalina.LifecycleState;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatgptRequest {

    private String model;

    private List<Message> messages;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Message {
        private String role;
        private String content;
    }


}
