package com.example.reservation.controller;

import com.example.reservation.dto.*;
import com.example.reservation.repository.ReserveRepository;
import com.example.reservation.service.*;
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
@RequestMapping("/reserve")
public class ReserveController {
    private final ReserveService reserveService;
    private final ReserveRepository reserveRepository;
    private final ReserveWaitService reserveWaitService;
    private final RoomService roomService;
    private final MemberService memberService;
    private final CouponService couponService;
    private final ReserveStatusService reserveStatusService;

    private final MessageService messageService;  // 추가


    @GetMapping
    public String book() {
        return "reservePages/reserve";
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        List<RoomDTO> roomDTOList = roomService.findAll();
        return new ResponseEntity<>(roomDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable("id") Long roomId,
                                   @RequestParam("checkInDate") String checkInDate,
                                   @RequestParam("checkOutDate") String checkOutDate) {
        System.out.println("roomId = " + roomId + ", checkInDate = " + checkInDate + ", checkOutDate = " + checkOutDate);
        ReserveDTO reserveDTO = reserveService.find(roomId, checkInDate, checkOutDate);
        return new ResponseEntity<>(reserveDTO, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity findByIdAndMemberEntity(@RequestParam("id") Long id, @RequestParam("memberId") Long memberId) {
        System.out.println("id = " + id + ", memberId = " + memberId);
        ReserveDTO reserveDTO = reserveService.findByIdAndMemberEntity(id, memberId);

        if(reserveDTO!=null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/wait")
    public ResponseEntity wait(@ModelAttribute ReserveWaitDTO reserveWaitDTO) {
        ReserveWaitDTO find = reserveWaitService.findByMemberEntityAndReserveEntity(reserveWaitDTO);
        if(find!=null){
            return new ResponseEntity<>(reserveWaitDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping("/wait")
    public ResponseEntity waitSave(@ModelAttribute ReserveWaitDTO reserveWaitDTO) {
        reserveWaitService.save(reserveWaitDTO);
        return new ResponseEntity<>(reserveWaitDTO, HttpStatus.OK);
    }



    @GetMapping("/save")
    public String save(@RequestParam("roomId") Long roomId,
                       @RequestParam("checkInDate") String checkInDate,
                       @RequestParam("checkOutDate") String checkOutDate,
                       @RequestParam("persons") String persons,
                       @RequestParam("addPrice") String addPrice,
                       @RequestParam("price") String price,
                       HttpSession httpSession,
                       Model model) {
        System.out.println("roomId = " + roomId + ", checkInDate = " + checkInDate + ", checkOutDate = " + checkOutDate + ", persons = " + persons + ", addPrice = " + addPrice + ", price = " + price + ", model = " + model);
        RoomDTO roomDTO = roomService.findById(roomId);
        Object loginEmail = httpSession.getAttribute("loginEmail");
        String email = (String)loginEmail;
        MemberDTO memberDTO = memberService.findByMemberEmail(email);
        List<CouponDTO> couponDTOList = couponService.findByCoupon(memberDTO.getId());
        System.out.println(couponDTOList);
        model.addAttribute("member", memberDTO);
        model.addAttribute("room", roomDTO);
        model.addAttribute("couponList", couponDTOList);
        model.addAttribute("checkInDate", checkInDate);
        model.addAttribute("checkOutDate", checkOutDate);
        model.addAttribute("persons", persons);
        model.addAttribute("addPrice", addPrice);
        model.addAttribute("totalPrice", price);
        return "reservePages/pay";
    }


    @PostMapping("/coupon/{id}")
    public ResponseEntity coupon(@PathVariable("id")Long id){
        CouponDTO couponDTO = couponService.findByCouponId(id);
        return new ResponseEntity<>(couponDTO, HttpStatus.OK);
    }

    @PostMapping("/request")
    public ResponseEntity request(Long id){
        ReserveStatusDTO reserveStatusDTO = reserveStatusService.findByReserveEntity(id);
        reserveStatusDTO.setStatus(2);
        System.out.println(reserveStatusDTO);
        reserveStatusService.save(reserveStatusDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
