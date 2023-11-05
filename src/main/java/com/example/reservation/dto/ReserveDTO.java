package com.example.reservation.dto;


import com.example.reservation.entity.ReserveEntity;
import com.example.reservation.entity.RoomFileEntity;
import com.example.reservation.util.UtilClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class ReserveDTO {
    private Long id;
    private Long memberId;
    private String memberName;
    private Long roomId;
    private String checkInDate;
    private String checkOutDate;
    private int persons;
    private Long totalPrice;
    private int status;
    private String createdAt;


    private int fileAttached;
    private String roomName;
    private List<MultipartFile> RoomFileName;
    private List<String> originalFileName = new ArrayList<>();
    private List<String> storedFileName = new ArrayList<>();


    public static ReserveDTO toDTO(ReserveEntity reserveEntity) {


        ReserveDTO reserveDTO = new ReserveDTO();
        reserveDTO.setMemberName(reserveEntity.getMemberEntity().getMemberName());

        for (RoomFileEntity roomFileEntity: reserveEntity.getRoomEntity().getRoomFileEntityList()) {
            reserveDTO.getOriginalFileName().add(roomFileEntity.getOriginalFileName());
            reserveDTO.getStoredFileName().add(roomFileEntity.getStoredFileName());
        }
        reserveDTO.setRoomName(reserveEntity.getRoomEntity().getRoomName());
        reserveDTO.setFileAttached(reserveEntity.getRoomEntity().getFileAttached());
        reserveDTO.setRoomId(reserveEntity.getRoomEntity().getId());


        reserveDTO.setId(reserveEntity.getId());
        reserveDTO.setMemberId(reserveEntity.getMemberEntity().getId());
        reserveDTO.setCheckInDate(reserveEntity.getCheckInDate());
        reserveDTO.setCheckOutDate(reserveEntity.getCheckOutDate());
        reserveDTO.setPersons(reserveEntity.getPersons());
        reserveDTO.setTotalPrice(reserveEntity.getTotalPrice());
        reserveDTO.setStatus(reserveEntity.getReserveStatusEntity().getStatus());
        reserveDTO.setCreatedAt(UtilClass.dateTimeFormat(reserveEntity.getCreatedAt()));
        System.out.println("reserveDTO = " + reserveDTO);
        return reserveDTO;
    }

}
