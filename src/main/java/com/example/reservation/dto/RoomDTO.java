package com.example.reservation.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class RoomDTO {

    @Column
    private Long id;

    private String roomName;

    private String roomPrice;

    private int roomCount;

    private int fileAttached;

    private String roomType;

    private int capacity;

    private String roomInfo;

    private String roomItems;

}
