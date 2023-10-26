package com.example.reservation.entity;


import com.example.reservation.dto.RoomDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter(AccessLevel.PRIVATE)
@Getter
@ToString
@Entity


@Table(name = "room_table")
public class    RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String roomName;

    @Column(length = 50, nullable = false)
    private Long roomPrice;

    @Column
    private int fileAttached;

    @Column(length = 50, nullable = false)
    private String roomType;

    @Column
    private int capacity;

    @Column
    private int standardPerson;

    @Column(length = 500)
    private String roomInfo;

    @Column(length = 200)
    private String roomItems;

    @OneToMany(mappedBy = "roomEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RoomFileEntity> roomFileEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "roomEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ReserveEntity> reserveEntityList = new ArrayList<>();


    public static RoomEntity toSaveEntity(RoomDTO roomDTO) {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomInfo(roomDTO.getRoomInfo());
        roomEntity.setRoomItems(roomDTO.getRoomItems());
        roomEntity.setRoomName(roomDTO.getRoomName());
        roomEntity.setCapacity(roomDTO.getCapacity());
        roomEntity.setStandardPerson(roomDTO.getStandardPerson());
        roomEntity.setRoomPrice(roomDTO.getRoomPrice());
        roomEntity.setRoomType(roomDTO.getRoomType());
        roomEntity.setFileAttached(0);
        return roomEntity;
    }


    public static RoomEntity toSaveEntityWithFile(RoomDTO roomDTO) {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomInfo(roomDTO.getRoomInfo());
        roomEntity.setRoomItems(roomDTO.getRoomItems());
        roomEntity.setRoomName(roomDTO.getRoomName());
        roomEntity.setCapacity(roomDTO.getCapacity());
        roomEntity.setStandardPerson(roomDTO.getStandardPerson());
        roomEntity.setRoomPrice(roomDTO.getRoomPrice());
        roomEntity.setRoomType(roomDTO.getRoomType());
        roomEntity.setFileAttached(1);
        return roomEntity;
    }


    public static RoomEntity toUpdateEntity(RoomDTO roomDTO) {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setId(roomDTO.getId());
        roomEntity.setRoomInfo(roomDTO.getRoomInfo());
        roomEntity.setRoomItems(roomDTO.getRoomItems());
        roomEntity.setRoomName(roomDTO.getRoomName());
        roomEntity.setCapacity(roomDTO.getCapacity());
        roomEntity.setRoomPrice(roomDTO.getRoomPrice());
        roomEntity.setRoomType(roomDTO.getRoomType());
        return roomEntity;
    }
}

