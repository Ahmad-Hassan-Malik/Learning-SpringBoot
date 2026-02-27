package com.ahmadmalik.mySpringBootProject.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myCustomSwagger() {
        return new OpenAPI().info(
                new Info().title("Journal App APIs").description("By Ahmad"))
                .servers(Arrays.asList(new Server().url("http://localhost:8082").description("Production"),
                        new Server().url("http://localhost:8081").description("Development")));
    }
}
