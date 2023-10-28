package com.example.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kakaoPay")
public class PaymentController {

    @GetMapping("/kakaoPayInit")
    public String kakaoPayInit() {
        return "/kakaoPaymentPages/kakaoPayment";
    }

}
