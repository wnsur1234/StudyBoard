package com.grepp.board.app.model.service;

import com.grepp.board.app.controller.web.payload.LetterResponse;
import com.grepp.board.app.model.dto.LetterDTO;
import com.grepp.board.app.model.repository.LenderLetterRepository;
import com.grepp.board.infra.entity.Letter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LenderLetterService {

    private final LenderLetterRepository lenderLetterRepository;

    public List<LetterDTO> getAllLetter() {
        List<Letter> letter = lenderLetterRepository.findAll();

        return letter.stream().map(e -> LetterDTO.builder()
                .id(e.getId())
                .LetterTitle(e.getTitle())
                .story(e.getStory())
                .qrName(e.getQrName())
                .qrUrl(e.getQrUrl())
                .size(e.getSize())
                .build()).toList();
    }
}
