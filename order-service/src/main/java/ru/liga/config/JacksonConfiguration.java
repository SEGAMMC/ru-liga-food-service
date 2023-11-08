package ru.liga.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JacksonConfiguration {
    @Bean
    public ObjectMapper objectMapper() {
        new ObjectMapper().registerModule(new JavaTimeModule());

        return JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
    }
}