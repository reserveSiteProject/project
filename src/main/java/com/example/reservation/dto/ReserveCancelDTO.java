package com.example.reservation.dto;

import com.example.reservation.entity.ReserveCancelEntity;
import com.example.reservation.entity.ReserveStatusEntity;
import lombok.Data;

@Data
public class ReserveCancelDTO {
    private Long id;
    private Long memberId;
    private Long reserveId;


    public static ReserveCancelDTO toDTO(ReserveCancelEntity reserveCancelEntity){
        ReserveCancelDTO reserveCancelDTO = new ReserveCancelDTO();
        reserveCancelDTO.setId(reserveCancelEntity.getId());
        reserveCancelDTO.setReserveId(reserveCancelEntity.getReserveId());
        reserveCancelDTO.setMemberId(reserveCancelEntity.getMemberId());
        return reserveCancelDTO;
    }

}
