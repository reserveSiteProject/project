package com.example.reservation.controller;

import com.example.reservation.dto.CouponDTO;
import com.example.reservation.dto.MemberDTO;
import com.example.reservation.dto.ReserveDTO;
import com.example.reservation.dto.RoomDTO;
import com.example.reservation.entity.MemberEntity;
import com.example.reservation.service.CouponService;
import com.example.reservation.service.MemberService;
import com.example.reservation.service.ReserveService;
import com.example.reservation.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final CouponService couponService;
    private final MemberService memberService;
    private final RoomService roomService;
    private final ReserveService reserveService;

    @GetMapping("/reserve")
    public String reserve(Model model){
        List<ReserveDTO> reserveDTOList = reserveService.findAll();
        System.out.println(reserveDTOList);
        model.addAttribute("memberReserveList", reserveDTOList);
        return "adminPages/reserve";
    }

    @PostMapping("/reserve/{id}")
    public String reserve(@PathVariable("id")Long id){
        return "redirect:/admin/reserve";
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
    public String couponSave(@ModelAttribute CouponDTO couponDTO){
        MemberEntity memberEntity = couponService.findById(couponDTO.getMemberId());
        couponService.save(couponDTO, memberEntity);
        return "redirect:/admin/manage";
    }

    @GetMapping("/room/save")
    public String roomSave(){
        return "adminPages/roomSave";
    }

    @PostMapping("/room/save")
    public String roomSave(@ModelAttribute RoomDTO roomDTO) throws IOException {
        roomService.save(roomDTO);
        return "adminPages/room";
    }





}
