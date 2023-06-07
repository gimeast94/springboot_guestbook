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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")
@Log4j2
public class GuestbookController {

    private final GuestbookService guestbookService;

    public GuestbookController(GuestbookService guestbookService) {
        this.guestbookService = guestbookService;
    }

    @GetMapping("/list")
    public String list(PageRequestDto pageRequestDto, Model model) {

        log.info("list............." + pageRequestDto);
        PageResultDto<GuestbookDto, Guestbook> result = guestbookService.getList(pageRequestDto);

        model.addAttribute("result", result);

        return"/guestbook/list";
    }
}
