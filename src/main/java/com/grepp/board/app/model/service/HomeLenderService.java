package com.grepp.board.app.model.service;

import com.grepp.board.app.model.dto.HomeDTO;
import com.grepp.board.app.model.repository.HomeLenderRepository;
import com.grepp.board.infra.entity.Home;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeLenderService {

    private final HomeLenderRepository homeLenderRepository;

    public HomeDTO getHomeById(Long homeId) {

        Home lender = homeLenderRepository.findAllById(homeId);


        return HomeDTO.builder()
                .id(lender.getId())
                .title(lender.getTitle())
                .introduce_story(lender.getIntroduce_story())
                .category(lender.getCategory())
                .todayComment(lender.getToday_comment())
                .build();
    }
}