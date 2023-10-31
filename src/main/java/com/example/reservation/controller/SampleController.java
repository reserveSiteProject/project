package com.example.reservation.controller;

import com.example.reservation.dto.ReserveDTO;
import com.example.reservation.service.KakaoPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
public class SampleController {

    @Setter(onMethod_ = @Autowired)
    private KakaoPay kakaopay;


    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {

    }

    @PostMapping("/kakaoPay")
    public String kakaoPay(@ModelAttribute ReserveDTO reserveDTO) {
        System.out.println("reserveDTO = " + reserveDTO);
        log.info("kakaoPay post............................................");

        return "redirect:" + kakaopay.kakaoPayReady(reserveDTO);

    }

    @GetMapping("/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        System.out.println("kakaoPaySuccess get............................................");
        System.out.println("kakaoPaySuccess pg_token : " + pg_token);

        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token));
        return "reservePages/kakaoPaySuccess";
    }

    @GetMapping("/kakaoPayCancel")
    public String KakaoPayCancle(){
        System.out.println(kakaopay.KakaoCancle());
        return "index";
    }
}
