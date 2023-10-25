package com.example.reservation.controller;

import com.example.reservation.dto.RoomDTO;
import com.example.reservation.service.ReserveService;
import com.example.reservation.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
//    public String list(@RequestParam("checkInDate") String checkInDate, @RequestParam("checkOutDate") String checkOutDate) {
    public ResponseEntity list(Model model) {
        List<RoomDTO> roomDTOList = roomService.findAll();
        System.out.println("roomDTOList = " + roomDTOList);
        return new ResponseEntity<>(roomDTOList, HttpStatus.OK);
    }
}
