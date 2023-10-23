package com.example.reservation.controller;

import com.example.reservation.dto.MemberDTO;
import com.example.reservation.dto.RoomDTO;
import com.example.reservation.service.AdminService;
import com.example.reservation.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final MemberService memberService;

    @GetMapping("/book")
    public String book(){
        return "adminPages/book";
    }

    @PostMapping("/book/{id}")
    public String book(@PathVariable("id")Long id){
        return "redirect:/admin/book";
    }

    @GetMapping("/manage")
    public String manage(Model model,
                         @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                         @RequestParam(value = "q", required = false, defaultValue = "") String q) {
      Page<MemberDTO> memberDTOList = memberService.findAll(page, q);

        int blockLimit = 3;
        int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < memberDTOList.getTotalPages()) ? startPage + blockLimit - 1 : memberDTOList.getTotalPages();

        model.addAttribute("memberList", memberDTOList);
        model.addAttribute("endPage", endPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("page", page);
        model.addAttribute("q", q);
        return "adminPages/manage";
    }

    @PostMapping("/coupon/save")
    public String couponSave(){
        return "redirect:/admin/manage";
    }

    @GetMapping("/room/save")
    public String roomSave(){

        return "adminPages/roomSave";
    }

    @PostMapping("/room/save")
    public String roomSave(@ModelAttribute RoomDTO roomDTO){

        return "redirect:/room";
    }





}
