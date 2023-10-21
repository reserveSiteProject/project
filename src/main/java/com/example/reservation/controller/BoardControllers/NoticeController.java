package com.example.reservation.controller.BoardControllers;

import com.example.reservation.dto.NoticeDTO;
import com.example.reservation.service.boardServices.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class NoticeController {

    private final NoticeService noticeService;
    @GetMapping("/notice")
    public String notice() {
        return "boardPages/notice";
    }
    @GetMapping("/notice/save")
    public String noticeSave() {
        return "boardPages/noticeSave";
    }

    @PostMapping("/notice/save")
    public String noticeSave(@ModelAttribute NoticeDTO noticeDTO) throws IOException {
        noticeService.save(noticeDTO);
        return "redirect:/board/notice";
    }
}
