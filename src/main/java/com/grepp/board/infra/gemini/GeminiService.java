// src/main/java/com/grepp/board/app/service/GeminiService.java
package com.grepp.board.infra.gemini;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeminiService {

    private final Client client;

    public String generateText(String prompt) {
        try {
            GenerateContentResponse res =
                    client.models.generateContent(
                            "gemini-2.5-flash",
                            prompt,
                            null
                    );
            return res.text();
        } catch (Exception e) {
            // 필요 시 로깅/커스텀 예외로 변환
            return "오류가 발생했습니다: " + e.getMessage();
        }
    }
}
