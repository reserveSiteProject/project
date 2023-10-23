package com.example.reservation.entity;

import com.example.reservation.dto.RoomDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter(AccessLevel.PRIVATE)
@Getter
@ToString
@Entity

@Table(name = "room_file_table")
public class RoomFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private RoomEntity roomEntity; // 이것을 부모로서 참조하겠다.

    public static RoomFileEntity toSaveEntity(String originalFileName, String storedFileName, RoomEntity roomEntity) {
        RoomFileEntity roomFileEntity = new RoomFileEntity();
        roomFileEntity.setOriginalFileName(originalFileName);
        roomFileEntity.setStoredFileName(storedFileName);
        roomFileEntity.setRoomEntity(roomEntity);
        return roomFileEntity;
    }

    public static RoomFileEntity toUpdateEntity(RoomDTO roomDTO, String originalFileName, String storedFileName, RoomEntity roomEntity) {
        RoomFileEntity roomFileEntity = new RoomFileEntity();
        roomFileEntity.setOriginalFileName(originalFileName);
        roomFileEntity.setStoredFileName(storedFileName);
        roomFileEntity.setRoomEntity(roomEntity);
        roomFileEntity.setId(roomDTO.getId());
        return roomFileEntity;
    }
}
