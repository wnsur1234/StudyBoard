package com.grepp.board.app.model.service;

import com.grepp.board.app.model.dto.HomeDTO;
import com.grepp.board.app.model.repository.HomeLenderRepository;
import com.grepp.board.infra.entity.Home;
import com.grepp.board.infra.entity.SetConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HomeLenderService {

    private final HomeLenderRepository homeLenderRepository;
    private final SetConfigService setConfigService;

    public HomeDTO getHomeById(Long homeId) {

        Home lender;

        SetConfig sc= new SetConfig();

        Home defHomeId = sc.getDefaultId();
        System.out.println(defHomeId);

        if(homeId == null) {
            lender = homeLenderRepository.findById(setConfigService.getHomeId())
                    .orElseThrow(()->new IllegalStateException("기본 설정 SetConfig가 없습니다."));

        }else{
            lender = homeLenderRepository.findById(homeId)
                    .orElseThrow(()->new IllegalArgumentException("존재하지 않는 ID 입니다."));
        }

        return HomeDTO.builder()
                .id(lender.getId())
                .title(lender.getTitle())
                .introduce_story(lender.getIntroduce_story())
                .category(lender.getCategory())
                .todayComment(lender.getToday_comment())
                .build();
    }
}