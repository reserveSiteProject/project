package com.example.reservation.controller;

import com.example.reservation.dto.MemberDTO;
import com.example.reservation.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final MemberService memberService;



    @GetMapping("/error")
    public String index(){
        return "error";
    }

    @GetMapping("/{id}")
    public String chattingRoom(@PathVariable Long id, HttpSession session, Model model){
        MemberDTO memberDTO = memberService.findById(id);
        if(memberDTO.getMemberEmail().equals("admin@admin.com")) {
            model.addAttribute("name", "master");
        }else if(!(memberDTO.getMemberEmail().equals("admin@admin.com")) && memberDTO!=null){
            model.addAttribute("name", memberDTO.getMemberEmail());
        }
        if(model.getAttribute("name") ==null){
            return "error";
        }else{
            return "popup";
        }
    }
}