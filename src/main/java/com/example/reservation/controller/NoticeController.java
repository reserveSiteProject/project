package com.example.reservation.controller;

import com.example.reservation.dto.NoticeDTO;
import com.example.reservation.service.MemberService;
import com.example.reservation.service.MessageService;
import com.example.reservation.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class NoticeController {

    private final NoticeService noticeService;
    private final MessageService messageService;

    private final MemberService memberService;

    @GetMapping("/notice")
    public String notice(Model model, HttpSession session) throws IOException {
//        session.getAttribute("loginEmail");
        List<NoticeDTO> noticeDTOList = noticeService.notice();
        model.addAttribute("noticeList", noticeDTOList);
        return "boardPages/notice";
    }
    @GetMapping("/notice/save")
    public String noticeSave(HttpSession session) {
//        session.getAttribute("loginEmail");
        return "boardPages/noticeSave";
    }

    @PostMapping("/notice/save")
    public String noticeSave(@ModelAttribute NoticeDTO noticeDTO) throws IOException {
        noticeService.save(noticeDTO);
//
//        String memberMobile = memberService.
//
//        //문자 발송 메서드 호출
//        messageService.sendOneReservationComplete(memberMobile);

        return "redirect:/board/notice";
    }

    @GetMapping("/notice/{id}")
    public String noticeDetail(@PathVariable("id") Long id, Model model) throws IOException {
        NoticeDTO noticeDTO = noticeService.findById(id);
        model.addAttribute("notice", noticeDTO);
        return "boardPages/noticeDetail";
    }
}
