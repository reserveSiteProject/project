package com.example.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
@RequestMapping("/jq")
public class KakaoController {


    @RequestMapping("/jq.cls")
    public ModelAndView main(ModelAndView mv, HttpSession s, RedirectView rv){
        mv.setViewName("jq/test");
        return mv;
    }
    @RequestMapping("/pay.cls")
    public ModelAndView serve(ModelAndView mv, HttpSession s, RedirectView rv){
        mv.setViewName("jq/serve");
        return mv;
    }
    @GetMapping("/kakao")
    public String kakao(){
        return "kakao";
    }


    @PostMapping("/kakaopay.cls")
    @ResponseBody
    public String kakaopay() throws IOException {
        URL address = new URL("https://kapi.kakao.com/v1/payment/ready");
        HttpURLConnection connection = (HttpURLConnection) address.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization","KakaoAK 8195b09f32e4faf23a4af02d67c6fd58");
        connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        connection.setDoOutput(true);
        String parameter = "cid=TC0ONETIME&partner_order_id=partner_order_id&partner_user_id=partner_user_id&item_name=초코파이&quantity=1&total_amount=2200&vat_amount=200&tax_free_amount=0&approval_url=https://localhost/cls/jq/pay.cls/success&fail_url=https://localhost/cls/jq/pay.cls/fail&cancel_url=https://localhost/cls/jq/pay.cls/cancel";
        OutputStream putter = connection.getOutputStream();
        DataOutputStream dataPutter = new DataOutputStream(putter);
        dataPutter.writeBytes(parameter);
        dataPutter.close();

        int resultCode = connection.getResponseCode();

        InputStream receiver;
        if(resultCode == 200){
            receiver = connection.getInputStream();
        }else{
            receiver = connection.getErrorStream();
        }
        InputStreamReader reader = new InputStreamReader(receiver);
        BufferedReader bufferedReader = new BufferedReader(reader);
        return bufferedReader.readLine();
    }
}
