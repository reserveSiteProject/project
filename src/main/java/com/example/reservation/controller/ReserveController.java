package com.example.reservation.controller;

import com.example.reservation.service.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reserve")
public class ReserveController {
    private final ReserveService reserveService;

    @GetMapping
    public String book() {
        return "reservePages/reserve";
    }

    @GetMapping("/list")
    public String list(@RequestParam("checkInDate") String checkInDate, @RequestParam("checkOutDate") String checkOutDate) {
        System.out.println("checkInDate = " + checkInDate + ", checkOutDate = " + checkOutDate);
        return "";
    }
}
