package com.example.reservation.controller;

import com.example.reservation.dto.ReviewDTO;
import com.example.reservation.service.MyPagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myPages")
public class MyPagesController {
    private final MyPagesService myPagesService;

    @GetMapping("/review")
    public String review(HttpSession session, Model model){
        Object loginEmail = session.getAttribute("memberDTO");
        String memberDTO = (String)loginEmail;
        List<ReviewDTO> reviewList = myPagesService.findAll(memberDTO);
        model.addAttribute("reviewList", reviewList);
        return "MyPages/review";
    }
    @PostMapping("/review/{id}")
    public String reviewDetail(@PathVariable("id") Long id, Model model){
        ReviewDTO reviewDTO = myPagesService.findById(id);
        model.addAttribute("review",reviewDTO);
        return "MyPages/reviewDetail";
    }

    @GetMapping("/review/update/{id}")
    public String reviewUpdate(@PathVariable("id") Long id, Model model){
        ReviewDTO reviewDTO = myPagesService.findById(id);
        model.addAttribute("review", reviewDTO);
        return "MyPages/reviewUpdate";
    }
    @PutMapping("/review/{id}")
    public ResponseEntity reviewUpdate(@ModelAttribute ReviewDTO reviewDTO){
        boolean result = myPagesService.save(reviewDTO);
        if(result){
            return new ResponseEntity("수정 완료", HttpStatus.OK);
        }else{
            return new ResponseEntity("수정 실패", HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/review/{id}")
    public String reviewDelete(@PathVariable("id") Long id){
        myPagesService.deleteById(id);
        return "redirect:/myPages/review";
    }
}
