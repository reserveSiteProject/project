package com.example.reservation.controller;

import com.example.reservation.dto.RoomDTO;
import com.example.reservation.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/list")
    public String list(Model model){
        List<RoomDTO> roomDTOList = roomService.findAll();
        model.addAttribute("roomList", roomDTOList);
        return "roomPages/roomList";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id") Long id){
        RoomDTO roomDTO = roomService.findById(id);
        model.addAttribute("roomDTO", roomDTO);
        return "roomPages/roomDetail";
    }
}
