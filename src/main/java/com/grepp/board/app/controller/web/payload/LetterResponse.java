package com.grepp.board.app.controller.web.payload;

import com.grepp.board.app.model.dto.LetterDTO;
import com.grepp.board.infra.entity.Letter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LetterResponse {
    private Long letterId;
    private String title;
    private String story;
    private String qrUrl;
    private String qrName;
    private Integer size;
    private String qrBase64;

    public static LetterResponse fromDTO(LetterDTO letter) {
        return LetterResponse.builder()
                .letterId(letter.getId())
                .story(letter.getStory())
                .title(letter.getLetterTitle())
                .qrUrl(letter.getQrUrl())
                .qrName(letter.getQrName())
                .size(letter.getSize())
                .build();
    }
    public static List<LetterResponse> fromDTOs(List<LetterDTO> letters) {
        return letters.stream().map(LetterResponse::fromDTO).toList();
    }
}
