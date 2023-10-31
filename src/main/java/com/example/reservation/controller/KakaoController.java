package com.example.reservation.controller;

import com.example.reservation.dto.PaymentDTO;
import com.example.reservation.dto.ReserveDTO;
import com.example.reservation.service.KakaoPay;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Setter;
import lombok.extern.java.Log;

import javax.servlet.http.HttpSession;

@Log
@Controller
@RequiredArgsConstructor
public class KakaoController {

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

    @PostMapping("/payment/save")
    public ResponseEntity paymentSave(@ModelAttribute PaymentDTO paymentDTO){
        System.out.println("paymentDTO = " + paymentDTO);
        Long aLong = kakaopay.paymentSave(paymentDTO);
        System.out.println(aLong);
        if(aLong != null){
            return new ResponseEntity<>("저장완료", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("저장실패", HttpStatus.CONFLICT);
        }
    }

}
