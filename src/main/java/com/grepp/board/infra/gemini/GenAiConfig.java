// src/main/java/com/grepp/board/config/GenAiConfig.java
package com.grepp.board.infra.gemini;

import com.google.genai.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenAiConfig {

    @Value("${gemini-key}")
    private String apiKey;

    @Bean
    public Client genAiClient() {
        if (apiKey != null && !apiKey.isBlank()) {
            return Client.builder().apiKey(apiKey).build();
        }
        return new Client();
    }
}
