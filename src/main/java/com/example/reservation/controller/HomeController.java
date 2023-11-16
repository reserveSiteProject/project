package com.example.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/direction")
    public String direction() {
        return "direction";
    }

    @GetMapping("/spot")
    public String spot() {
        return "spot";
    }

    @GetMapping("/error/404")
    public String error(){
        return "error";
    }

}
