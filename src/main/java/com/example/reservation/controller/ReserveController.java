package com.example.reservation.controller;

import com.example.reservation.dto.ReserveDTO;
import com.example.reservation.dto.RoomDTO;
import com.example.reservation.service.ReserveService;
import com.example.reservation.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reserve")
public class ReserveController {
    private final ReserveService reserveService;
    private final RoomService roomService;

    @GetMapping
    public String book() {
        return "reservePages/reserve";
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        List<RoomDTO> roomDTOList = roomService.findAll();
        return new ResponseEntity<>(roomDTOList, HttpStatus.OK);
    }



    @GetMapping("/save")
    public String save(@RequestParam("roomId") Long roomId,
                       @RequestParam("checkInDate") String checkInDate,
                       @RequestParam("checkOutDate") String checkOutDate,
                       @RequestParam("persons") String persons,
                       @RequestParam("addPrice") String addPrice,
                       Model model) {
        System.out.println("roomId = " + roomId + ", checkInDate = " + checkInDate + ", checkOutDate = " + checkOutDate + ", persons = " + persons + ", addPrice = " + addPrice + ", model = " + model);
        RoomDTO roomDTO = roomService.findById(roomId);
        System.out.println("roomDTO = " + roomDTO);
        model.addAttribute("room", roomDTO);
        model.addAttribute("checkInDate", checkInDate);
        model.addAttribute("checkOutDate", checkOutDate);
        model.addAttribute("persons", persons);
        model.addAttribute("addPrice", addPrice);
        return "reservePages/pay";
    }
}
