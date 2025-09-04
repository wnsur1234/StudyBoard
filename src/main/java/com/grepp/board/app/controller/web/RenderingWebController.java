package com.grepp.board.app.controller.web;

import com.grepp.board.app.controller.web.payload.*;
import com.grepp.board.app.model.qrcode.service.QrService;
import com.grepp.board.app.model.service.HomeLenderService;
import com.grepp.board.app.model.service.LenderLetterService;
import com.grepp.board.app.model.service.UsersService;
import com.grepp.board.app.model.service.WriteLetterService;
import com.grepp.board.infra.error.DuplicateEmailException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
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
    @Value("${qrcode.url}")
    private String qrUrl;

    @Value("${qrcode.size}")
    private int qrSize;

    private final HomeLenderService homeLenderService;
    private final WriteLetterService writeLetterService;
    private final LenderLetterService lenderLetterService;
    private final UsersService usersService;
    private final QrService qrService;

    // 카카오 지도
    @GetMapping("/map")
    public String map() {
        return "board/kokoMap";
    }

    // 화면을 나타낼 때 특정 데이터가 필요한것이므로
    // id 값을 받아서 home 엔티티에 id 값을 반환 하도록 변경해야함
    @GetMapping()
    public String render(
            @RequestParam(name="id",required = false) Long homeId,
            Model model)
    {
        RenderResponse home = RenderResponse.fromDTO(homeLenderService.getHomeById(homeId));
        List<LetterResponse> letters = LetterResponse.fromDTOs(lenderLetterService.getAllLetter());
        String fixedBase64 = qrService.toBase64Png(qrUrl,qrSize);

        for(LetterResponse i : letters){
            if(i.getQrUrl() != null && !i.getQrUrl().isBlank()){
                int size = (i.getSize() != null) ? i.getSize() : qrSize;
                i.setQrBase64(qrService.toBase64Png(i.getQrUrl(),size));
            }
        }

        model.addAttribute("home", home);
        model.addAttribute("letters", letters);
        model.addAttribute("qrBase64", fixedBase64);
        model.addAttribute("qrUrl", qrUrl);
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
    @GetMapping("/login")
    public String login(){
        return "security/login";
    }
    @GetMapping("/signup")
    public String signup(){
        return "security/signup";
    }
    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute SignupRequest signupRequest,
                         BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "security/signup";
        }
        try {
            usersService.signup(signupRequest);
        }catch (DuplicateEmailException e){
            bindingResult.rejectValue("email","duplicate",e.getMessage());
            return "security/signup";
        }catch (DataIntegrityViolationException e){
            // unique 제약 보루
            bindingResult.rejectValue("email","duplicate","이미 사용중인 이메일입니다.");
            return "security/signup";
        }
        return "redirect:/login?signup";
    }
    @PostMapping("/download")
    public ResponseEntity<byte[]> download(@RequestParam String base64,
                                           @RequestParam String name) {
        byte[] bytes = java.util.Base64.getDecoder().decode(base64);

        ContentDisposition cd = ContentDisposition.attachment()
                .filename(name + ".png", StandardCharsets.UTF_8) // ★ 핵심
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, cd.toString())
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }
}
