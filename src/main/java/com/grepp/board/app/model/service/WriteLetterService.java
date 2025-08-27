package com.grepp.board.app.model.service;

import com.grepp.board.app.controller.web.payload.LetterRequest;
import com.grepp.board.app.controller.web.payload.LetterResponse;
import com.grepp.board.app.model.repository.WriteLetterRepository;
import com.grepp.board.infra.entity.Home;
import com.grepp.board.infra.entity.Letter;
import lombok.RequiredArgsConstructor;
import org.attoparser.dom.Text;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WriteLetterService {

    private final WriteLetterRepository writeLetterRepository;
    private final HomeLenderService homeLenderService;

    public void sendLetter(LetterRequest letterRequest) {
        Letter letter = new Letter();
        Home home = homeLenderService.getReferenceById(letterRequest.getHomeId());
        letter.setHome(home);
        letter.setTitle(letterRequest.getTitle());
        letter.setStory(letterRequest.getStory());
        letter.setCategory(letterRequest.getCategory());
        letter.setCreatedAt(LocalDateTime.now());
        writeLetterRepository.save(letter);
    }

}
