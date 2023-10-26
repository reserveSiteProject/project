package com.example.reservation.dto;

import com.example.reservation.entity.ReviewFileEntity;
import com.example.reservation.entity.RoomEntity;
import com.example.reservation.entity.RoomFileEntity;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Data
public class RoomDTO {

    @Column
    private Long id;

    private String roomName;

    private Long roomPrice;

    private String roomType;

    private int capacity;
    private int standardPerson;

    private String roomInfo;

    private String roomItems;





    private List<MultipartFile> RoomFileName;
    private int fileAttached;
    private List<String> originalFileName = new ArrayList<>();
    private List<String> storedFileName = new ArrayList<>();




    public static RoomDTO toDTO(RoomEntity roomEntity){
        DecimalFormat df = new DecimalFormat("###,###");
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(roomEntity.getId());
        roomDTO.setRoomInfo(roomEntity.getRoomInfo());
        roomDTO.setRoomItems(roomEntity.getRoomItems());
        roomDTO.setRoomName(roomEntity.getRoomName());
        roomDTO.setRoomPrice(df.format(Integer.parseInt(roomEntity.getRoomPrice())));
        roomDTO.setRoomType(roomEntity.getRoomType());
        roomDTO.setCapacity(roomEntity.getCapacity());
        roomDTO.setStandardPerson(roomEntity.getStandardPerson());
        if (roomEntity.getFileAttached() == 1) {
            for (RoomFileEntity roomFileEntity : roomEntity.getRoomFileEntityList()) {
                roomDTO.getOriginalFileName().add(roomFileEntity.getOriginalFileName());
                roomDTO.getStoredFileName().add(roomFileEntity.getStoredFileName());
            }
            roomDTO.setFileAttached(1);
        }else {
            roomDTO.setFileAttached(0);
        }
        System.out.println(roomDTO);
        return roomDTO;
    }



}
