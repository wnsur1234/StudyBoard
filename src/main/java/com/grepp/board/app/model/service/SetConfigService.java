package com.grepp.board.app.model.service;

import com.grepp.board.app.model.repository.SetConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SetConfigService {

    private final SetConfigRepository setConfigRepository;

    public Long getHomeId(){
        return setConfigRepository.findTopByOrderByIdAsc().getDefaultId().getId();
    }
}
