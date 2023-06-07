package com.gimeast.guestbook.controller;

import com.gimeast.guestbook.data.dto.GuestbookDto;
import com.gimeast.guestbook.data.dto.PageRequestDto;
import com.gimeast.guestbook.data.dto.PageResultDto;
import com.gimeast.guestbook.data.entity.Guestbook;
import com.gimeast.guestbook.service.GuestbookService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/guestbook")
@Log4j2
public class GuestbookController {

    private final GuestbookService guestbookService;

    public GuestbookController(GuestbookService guestbookService) {
        this.guestbookService = guestbookService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDto pageRequestDto, Model model) {

        log.info("[list] pageRequestDto : " + pageRequestDto);
        PageResultDto<GuestbookDto, Guestbook> result = guestbookService.getList(pageRequestDto);

        model.addAttribute("result", result);

    }

    @GetMapping("/register")
    public void register() {}

    @PostMapping("/register")
    public String registerPost(GuestbookDto guestbookDto, RedirectAttributes redirectAttributes) {
        log.info("[registerPost] guestbookDto : " + guestbookDto);
        Long gno = guestbookService.register(guestbookDto);
        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/guestbook/list";
    }

    @GetMapping("/read")
    public void read(Long gno, PageRequestDto pageRequestDto, Model model) {
        log.info("[read] gno : " + gno);

        GuestbookDto dto = guestbookService.read(gno);

        model.addAttribute("dto", dto);
        model.addAttribute("pageRequestDto", pageRequestDto);
    }



}
