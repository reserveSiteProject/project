package com.example.reservation.controller;

import com.example.reservation.dto.RoomDTO;
import com.example.reservation.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {
    private RoomService roomService;

    @GetMapping("/list")
    public String list(){
        List<RoomDTO> roomDTOList = roomService.findAll();
        return "roomPages/roomList";
    }
}
