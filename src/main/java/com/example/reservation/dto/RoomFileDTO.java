package com.example.reservation.dto;

import com.example.reservation.entity.RoomFileEntity;
import lombok.Data;

@Data
public class RoomFileDTO {
    private Long id;
    private String originalFileName;
    private String storedFileName;
    private Long roomId;

    public static RoomFileDTO toDTO(RoomFileEntity roomFileEntity, Long id) {
        RoomFileDTO roomFileDTO = new RoomFileDTO();
        roomFileDTO.setId(roomFileEntity.getId());
        roomFileDTO.setRoomId(id);
        roomFileDTO.setOriginalFileName(roomFileEntity.getOriginalFileName());
        roomFileDTO.setStoredFileName(roomFileEntity.getStoredFileName());
        return roomFileDTO;

    }
}
