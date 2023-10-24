package com.example.reservation.dto;


import com.example.reservation.entity.ReserveEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public static ReserveDTO toDTO(ReserveEntity reserveEntity) {
        ReserveDTO reserveDTO = new ReserveDTO();
        reserveDTO.setId(reserveEntity.getId());
        reserveDTO.setMemberId(reserveEntity.getMemberEntity().getId());
        reserveDTO.setRoomId(reserveEntity.getRoomEntity().getId());
        reserveDTO.setCheckInDate(reserveEntity.getCheckInDate());
        reserveDTO.setCheckOutDate(reserveEntity.getCheckOutDate());
        reserveDTO.setPersons(reserveDTO.getPersons());
        return reserveDTO;
    }

}
