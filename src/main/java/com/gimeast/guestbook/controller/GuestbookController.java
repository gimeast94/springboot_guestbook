package com.gimeast.guestbook.controller;

import com.gimeast.guestbook.data.dto.GuestbookDto;
import com.gimeast.guestbook.data.dto.PageRequestDto;
import com.gimeast.guestbook.data.dto.PageResultDto;
import com.gimeast.guestbook.data.entity.Guestbook;
import com.gimeast.guestbook.service.GuestbookService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public void read(Long gno,
                     @ModelAttribute("pageRequestDto") PageRequestDto pageRequestDto,
                     Model model,
                     @RequestParam(defaultValue = "R") String flag) {
        log.info("[read] gno : " + gno);
        log.info("[read] pageRequestDto : " + pageRequestDto);

        GuestbookDto dto = guestbookService.read(gno);

        model.addAttribute("flag", flag);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/remove")
    public String remove(@ModelAttribute("gno") Long gno) {
        log.info("[remove] gno : " + gno);

        guestbookService.remove(gno);

        return "redirect:/guestbook/list";
    }

    @PostMapping("/modify")
    public String modify(GuestbookDto guestbookDto, PageRequestDto pageRequestDto, RedirectAttributes redirectAttributes) {
        log.info("[modify] guestbookDto : " + guestbookDto);
        log.info("[modify] pageRequestDto : " + pageRequestDto);

        guestbookService.modify(guestbookDto);

        redirectAttributes.addAttribute("gno", guestbookDto.getGno());
        redirectAttributes.addAttribute("page", pageRequestDto.getPage());
        redirectAttributes.addAttribute("size", pageRequestDto.getSize());

        return "redirect:/guestbook/read";
    }


}
