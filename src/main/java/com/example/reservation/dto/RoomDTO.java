package com.example.reservation.dto;

import com.example.reservation.entity.RoomEntity;
import lombok.Data;

import javax.persistence.Column;

@Data
public class RoomDTO {

    private Long id;

    private String roomName;

    private String roomPrice;

    private int roomCount;

    private int fileAttached;

    private String roomType;

    private int capacity;

    private String roomInfo;

    private String roomItems;

    private String roomFileName;


    public static RoomDTO toDTO(RoomEntity roomEntity){
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(roomEntity.getId());
        roomDTO.setRoomCount(roomEntity.getRoomCount());
        roomDTO.setRoomInfo(roomEntity.getRoomInfo());
        roomDTO.setRoomItems(roomEntity.getRoomItems());
        roomDTO.setRoomName(roomDTO.getRoomName());
        roomDTO.setRoomPrice(roomDTO.getRoomPrice());
        roomDTO.setRoomType(roomDTO.getRoomType());
        roomDTO.setCapacity(roomDTO.getCapacity());
        roomDTO.setFileAttached(roomDTO.getFileAttached());

        return roomDTO;
    }


}





