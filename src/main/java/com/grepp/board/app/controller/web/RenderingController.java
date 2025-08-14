package com.grepp.board.app.controller.web;

import com.grepp.board.app.controller.web.payload.RenderResponse;
import com.grepp.board.app.model.service.HomeLenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class RenderingController {
    /**
     * TODO
     * board/new
     * boardList - post_id,title,createdAt,
     * 일단 지금 당장 model 로 보내야 하는 정보
     * ->
     */
    private final HomeLenderService homeLenderService;

    // 화면을 나타낼 때 특정 데이터가 필요한것이므로
    // id 값을 받아서 home 엔티티에 id 값을 반환 하도록 변경해야함
    @GetMapping()
    public String render(
            @RequestParam(name="id",required = false) Long homeId, Model model)
    {

        // 실험
        RenderResponse home = RenderResponse.fromDTO(homeLenderService.getHomeById(homeId));


        model.addAttribute("home", home);
        return "board/home";
    }

    /**
     * 자 뭐가 바뀌었다 과연 목록이 뜰까 확인 time
     * @param homeId
     * @param model
     * @return
     */

    @GetMapping("/letter/{id}")
    public String letter(@PathVariable("id") Long homeId,Model model){
        RenderResponse home = RenderResponse.fromDTO(homeLenderService.getHomeById(homeId));


        model.addAttribute("home", home);
        return "board/writer";
    }
}
