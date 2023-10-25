package com.example.reservation.dto;


import com.example.reservation.entity.ReserveEntity;
import com.example.reservation.entity.RoomFileEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ReserveDTO {
    private Long id;
    private Long memberId;
    private String memberName;
    private Long roomId;
    private RoomDTO roomDTO;
    private String checkInDate;
    private String checkOutDate;
    private int persons;

    private int fileAttached;
    private String roomName;
    private List<MultipartFile> RoomFileName;
    private List<String> originalFileName = new ArrayList<>();
    private List<String> storedFileName = new ArrayList<>();


    public static ReserveDTO toDTO(ReserveEntity reserveEntity) {
        ReserveDTO reserveDTO = new ReserveDTO();
        reserveDTO.setRoomName(reserveEntity.getRoomEntity().getRoomName());
        for (RoomFileEntity roomFileEntity: reserveEntity.getRoomEntity().getRoomFileEntityList()) {
            reserveDTO.getOriginalFileName().add(roomFileEntity.getOriginalFileName());
            reserveDTO.getStoredFileName().add(roomFileEntity.getStoredFileName());
        }
        reserveDTO.setId(reserveEntity.getId());
        reserveDTO.setFileAttached(reserveEntity.getRoomEntity().getFileAttached());
        reserveDTO.setMemberId(reserveEntity.getMemberEntity().getId());
        reserveDTO.setRoomId(reserveEntity.getRoomEntity().getId());
        reserveDTO.setCheckInDate(reserveEntity.getCheckInDate());
        reserveDTO.setCheckOutDate(reserveEntity.getCheckOutDate());
        reserveDTO.setPersons(reserveDTO.getPersons());
        System.out.println("reserveDTO = " + reserveDTO);
        return reserveDTO;
    }

}
