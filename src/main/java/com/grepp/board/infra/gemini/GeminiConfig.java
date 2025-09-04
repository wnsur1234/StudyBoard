package com.grepp.board.infra.gemini;

import com.google.genai.Client;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeminiConfig {

    public Client getClient() {
        return new Client();
    }

}
