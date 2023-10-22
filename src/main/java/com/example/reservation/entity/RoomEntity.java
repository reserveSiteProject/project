package com.example.reservation.entity;


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
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String roomName;


    @Column(length = 50, nullable = false)
    private String roomPrice;


    @Column
    private int roomCount;


    @Column
    private int fileAttached;


    @Column(length = 50, nullable = false)
    private String roomType;


    @Column
    private int capacity;


    @Column(length = 500)
    private String roomInfo;


    @Column(length = 200)
    private String roomItems;


    @OneToMany(mappedBy = "roomEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RoomFileEntity> roomFileEntityList = new ArrayList<>();


}

