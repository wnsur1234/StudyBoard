package com.grepp.board.app.model.dto;

import com.grepp.board.infra.emuns.Category;
import com.grepp.board.infra.emuns.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeDTO {
    private Long id;
    private String title;
    private Comment todayComment;
    private Category category;
    private String introduce_story;
}
