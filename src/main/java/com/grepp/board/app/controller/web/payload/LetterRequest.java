package com.grepp.board.app.controller.web.payload;

import com.grepp.board.infra.emuns.Category;
import com.grepp.board.infra.entity.Home;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LetterRequest {
    private Long homeId;
    private String title;
    private Category category;
    private String story;

}
