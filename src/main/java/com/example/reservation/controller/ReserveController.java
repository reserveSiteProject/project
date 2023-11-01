package com.example.reservation.controller;

import com.example.reservation.dto.ReserveDTO;
import com.example.reservation.dto.ReserveWaitDTO;
import com.example.reservation.dto.RoomDTO;
import com.example.reservation.entity.ReserveEntity;
import com.example.reservation.repository.ReserveRepository;
import com.example.reservation.service.MessageService;
import com.example.reservation.entity.ReserveWaitEntity;
import com.example.reservation.service.ReserveService;
import com.example.reservation.service.ReserveWaitService;
import com.example.reservation.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reserve")
public class ReserveController {
    private final ReserveService reserveService;
    private final ReserveRepository reserveRepository;
    private final ReserveWaitService reserveWaitService;
    private final RoomService roomService;

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
        ReserveDTO reserveDTO = reserveService.findByIdAndMemberEntity(id, memberId);
        ReserveWaitDTO reserveWaitDTO = reserveWaitService.findByMemberEntityAndReserveEntity(memberId, id);
        if(reserveDTO!=null || reserveWaitDTO!=null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/wait")
    public ResponseEntity wait(@ModelAttribute ReserveWaitDTO reserveWaitDTO) {
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
                       Model model) {
        System.out.println("roomId = " + roomId + ", checkInDate = " + checkInDate + ", checkOutDate = " + checkOutDate + ", persons = " + persons + ", addPrice = " + addPrice + ", price = " + price + ", model = " + model);
        RoomDTO roomDTO = roomService.findById(roomId);
        System.out.println("roomDTO = " + roomDTO);
        model.addAttribute("room", roomDTO);
        model.addAttribute("checkInDate", checkInDate);
        model.addAttribute("checkOutDate", checkOutDate);
        model.addAttribute("persons", persons);
        model.addAttribute("addPrice", addPrice);
        model.addAttribute("totalPrice", price);
        return "reservePages/pay";
    }

}
