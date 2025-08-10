package com.grepp.board.app.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RenderingController {
    /**
     * TODO
     * board/new
     * boardList - post_id,title,createdAt,
     * 일단 지금 당장 model 로 보내야 하는 정보
     * ->
     */
    @GetMapping
    public String render(Model model) {




        return "board/list";
    }
}
