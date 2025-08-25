package com.grepp.board.app.controller.web;

import com.grepp.board.app.controller.web.payload.LetterRequest;
import com.grepp.board.app.controller.web.payload.LetterResponse;
import com.grepp.board.app.controller.web.payload.RenderResponse;
import com.grepp.board.app.model.dto.LetterDTO;
import com.grepp.board.app.model.service.HomeLenderService;
import com.grepp.board.app.model.service.LenderLetterService;
import com.grepp.board.app.model.service.WriteLetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class RenderingWebController {
    /**
     * TODO
     * board/new
     * boardList - post_id,title,createdAt,
     * 일단 지금 당장 model 로 보내야 하는 정보
     * ->
     */
    private final HomeLenderService homeLenderService;
    private final WriteLetterService writeLetterService;
    private final LenderLetterService lenderLetterService;

    // 화면을 나타낼 때 특정 데이터가 필요한것이므로
    // id 값을 받아서 home 엔티티에 id 값을 반환 하도록 변경해야함
    @GetMapping()
    public String render(
            @RequestParam(name="id",required = false) Long homeId, Model model)
    {
        RenderResponse home = RenderResponse.fromDTO(homeLenderService.getHomeById(homeId));
        List<LetterResponse> letters = LetterResponse.fromDTOs(lenderLetterService.getAllLetter());
        model.addAttribute("home", home);
        model.addAttribute("letters", letters);
        System.out.println(letters);
        return "board/home";
    }
    @GetMapping("/letter")
    public String letter(@RequestParam(name="id",required = false) Long homeId,Model model){
        RenderResponse home = RenderResponse.fromDTO(homeLenderService.getHomeById(homeId));
        model.addAttribute("home", home);
        return "board/writer";
    }
    @PostMapping("/letter")
    public String sendLetter(LetterRequest letterRequest){
        writeLetterService.sendLetter(letterRequest);
        return "redirect:/";
    }
}
