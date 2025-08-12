package com.grepp.board.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.attoparser.dom.Text;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LetterDTO {
    private Long id;
    private Long homeId;
    private String LetterTitle;
    private Text story;
}
