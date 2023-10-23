package com.example.reservation.controller;

import com.example.reservation.dto.NoticeDTO;
import com.example.reservation.dto.RoomDTO;
import com.example.reservation.service.MemberService;
import com.example.reservation.service.NoticeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final NoticeService noticeService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/room")
    public String roomIndex(Model model) {
        return "roomList";
    }

    @GetMapping("/introduction")
    public String introduction() {
        return "introduction";
    }

    @GetMapping("/direction")
    public String direction() {
        return "direction";
    }

    @GetMapping("/spot")
    public String spot() {
        return "spot";
    }

}
