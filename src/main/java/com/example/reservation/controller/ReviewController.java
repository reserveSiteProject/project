package com.example.reservation.controller;

import com.example.reservation.dto.ReviewDTO;
import com.example.reservation.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/review")
    public String review() {
        return "boardPages/review";
    }

    @GetMapping("/review/save")
    public String reviewSave() {
        return "boardPages/reviewSave";
    }

    @PostMapping("/review/save")
    public String reviewSave(@ModelAttribute ReviewDTO reviewDTO) throws IOException {
        reviewService.save(reviewDTO);
        return "redirect:/board/review";
    }
}
