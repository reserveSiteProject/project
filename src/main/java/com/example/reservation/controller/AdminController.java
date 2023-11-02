package com.example.reservation.controller;

import com.example.reservation.dto.*;
import com.example.reservation.entity.MemberEntity;
import com.example.reservation.entity.ReserveCancelEntity;
import com.example.reservation.entity.ReserveEntity;
import com.example.reservation.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final CouponService couponService;
    private final MemberService memberService;
    private final RoomService roomService;
    private final ReserveService reserveService;
    private final ReserveWaitService reserveWaitService;
    private final ReserveCancelService reserveCancelService;
    private final MessageService messageService;

    @GetMapping("/reserve")
    public String reserve(Model model,
                          @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                          @RequestParam(value = "q", required = false, defaultValue = "") String q,
                          @RequestParam(value = "type", required = false, defaultValue = "") String type,
                          @RequestParam(value = "listCount", required = false, defaultValue = "10") int list,
                          @RequestParam(value = "checkInDate", required = false, defaultValue = "") String checkInDate) {
        System.out.println(checkInDate + "날짜");
        Page<ReserveDTO> reserveDTOList = reserveService.findAll(page, q, type, list , checkInDate);

        int blockLimit = 3;
        int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < reserveDTOList.getTotalPages()) ? startPage + blockLimit - 1 : reserveDTOList.getTotalPages();

        List<ReserveWaitDTO> ReserveWaitDTOList = reserveWaitService.findAll();

        List<ReserveCancelDTO> reserveCancelDTOList = reserveCancelService.findAll();


        model.addAttribute("cancelList", reserveCancelDTOList);
        model.addAttribute("waitList", ReserveWaitDTOList);
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

    @PutMapping("/reserve/{id}")
    public ResponseEntity reserve(@PathVariable("id")Long id) {
        System.out.println("여기냐 = " + id);
        ReserveDTO reserveDTO = reserveService.findById(id);  // 리저브 아이디찾기
        Long reserveId = reserveDTO.getId();
        Long memberId = reserveDTO.getMemberId();
        // 캔슬테이블 저장
        ReserveCancelDTO reserveCancelDTO = new ReserveCancelDTO();
        reserveCancelDTO.setReserveId(reserveId);
        reserveCancelDTO.setMemberId(memberId);
        reserveCancelService.save(reserveCancelDTO);

//        <----------예약대기 찾은 후 null이 아니라면 문자 발송 ---------->
        messageService.sendOneReservationCancel(memberId, reserveId);

        // 예약 취소한 사람에게 문자 보내기
        //해당 예약 건에 대해 다른사람이 예약대기를 한건수가 있으면 예약대기 신청을 한다.
        //사람에게 결제를 완료하라고 알림 문자 보내기

        if(reserveWaitService.findByReserveEntity(reserveId) != null) {
            messageService.sendOneReservationWaitEnd(reserveId);
        }
        // 마지막에 삭제
        reserveService.delete(id);
        return new ResponseEntity<>("취소가 완료되었습니다.",HttpStatus.OK);
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
