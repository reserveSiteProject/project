package com.example.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/room")
    public String roomIndex() {
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
