package com.grepp.board.app.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RenderingController {
    /**
     * TODO
     * board/new
     * boardList - post_id,title,createdAt,
     *
     */
    @GetMapping
    public String render() {
        return "board/list";
    }
}
