package com.grepp.board;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

public class GenerateTextFromTextInput {
  public static void main(String[] args) {

    // 그냥 이거 자체를 postman으로 확인 할 수 있는 거였음
    Client client = Client.builder().apiKey("REDACTED").build();
    GenerateContentResponse response =
        client.models.generateContent(
            "gemini-2.5-flash",
            "서울 맛집을 추천해줘",
            null);

    System.out.println(response.text());
  }
}