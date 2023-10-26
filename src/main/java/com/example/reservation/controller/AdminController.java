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

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final CouponService couponService;
    private final MemberService memberService;
    private final RoomService roomService;
    private final ReserveService reserveService;

    @GetMapping("/reserve")
    public String reserve(Model model,
                          @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                          @RequestParam(value = "q", required = false, defaultValue = "") String q,
                          @RequestParam(value = "type", required = false, defaultValue = "") String type,
                          @RequestParam(value = "listCount", required = false, defaultValue = "5") int list,
                          @RequestParam(value = "checkInDate", required = false, defaultValue = "") String checkInDate) {
        System.out.println(checkInDate + "날짜");
        Page<ReserveDTO> reserveDTOList = reserveService.findAll(page, q, type, list , checkInDate);

        int blockLimit = 3;
        int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < reserveDTOList.getTotalPages()) ? startPage + blockLimit - 1 : reserveDTOList.getTotalPages();

        model.addAttribute("memberReserveList", reserveDTOList);
        model.addAttribute("endPage", endPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("page", page);
        model.addAttribute("q", q);
        model.addAttribute("checkInDate", checkInDate);
        model.addAttribute("type", type);
        model.addAttribute("listCount", list);
        System.out.println(reserveDTOList);
        return "adminPages/reserve";
    }

    @PostMapping("/reserve")
    public String reserve(@ModelAttribute ReserveDTO reserveDTO) {
        reserveService.deleteById(reserveDTO.getId());
        System.out.println(reserveDTO.getId());
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
    public String couponSave(@ModelAttribute CouponDTO couponDTO) {
        MemberEntity memberEntity = couponService.findById(couponDTO.getMemberId());
        couponService.save(couponDTO, memberEntity);
        return "redirect:/admin/manage";
    }

    @GetMapping("/room/save")
    public String roomSave() {
        return "adminPages/roomSave";
    }

    @PostMapping("/room/save")
    public String roomSave(@ModelAttribute RoomDTO roomDTO) throws IOException {
        roomService.save(roomDTO);
        return "/index";
    }


}
