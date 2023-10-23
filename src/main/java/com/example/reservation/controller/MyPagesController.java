package com.example.reservation.controller;

import com.example.reservation.dto.ReviewDTO;
import com.example.reservation.service.MyPagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myPages")
public class MyPagesController {
    private final MyPagesService myPagesService;

    @GetMapping("/review")
    public String review(HttpSession session, Model model){
        Object loginEmail = session.getAttribute("memberDTO");
        String memberDTO = (String)loginEmail;
//        List<ReviewDTO> reviewList = myPagesService.findAll(memberDTO);
//        model.addAttribute("reviewList", reviewList);
        return "MyPages/review";
    }
}
