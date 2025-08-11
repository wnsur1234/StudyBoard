package com.grepp.board.app.controller.web.payload;

import com.grepp.board.app.model.dto.HomeDTO;
import com.grepp.board.infra.emuns.Category;
import com.grepp.board.infra.emuns.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RenderResponse {
    private Long id;
    private String title;
    private Comment todayComment;
    private Category category;
    private String introduce_story;

    public static RenderResponse fromDTO(HomeDTO homeDTO) {
        return RenderResponse.builder()
                .id(homeDTO.getId())
                .title(homeDTO.getTitle())
                .todayComment(homeDTO.getTodayComment())
                .category(homeDTO.getCategory())
                .introduce_story(homeDTO.getIntroduce_story())
                .build();
    }
    public static List<RenderResponse> fromDTOs(List<HomeDTO> homeDTOList) {
        return homeDTOList.stream().map(RenderResponse::fromDTO).toList();
    }
}
