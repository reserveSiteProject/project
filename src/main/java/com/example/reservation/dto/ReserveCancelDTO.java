package com.example.reservation.dto;

import com.example.reservation.entity.ReserveCancelEntity;
import com.example.reservation.util.UtilClass;
import lombok.Data;

@Data
public class ReserveCancelDTO {
    private Long id;
    private Long memberId;
    private Long reserveId;
    private String createdAt;


    public static ReserveCancelDTO toDTO(ReserveCancelEntity reserveCancelEntity){
        ReserveCancelDTO reserveCancelDTO = new ReserveCancelDTO();
        reserveCancelDTO.setId(reserveCancelEntity.getId());
        reserveCancelDTO.setReserveId(reserveCancelEntity.getReserveId());
        reserveCancelDTO.setMemberId(reserveCancelEntity.getMemberId());
        reserveCancelDTO.setCreatedAt(UtilClass.dateTimeFormat(reserveCancelEntity.getCreatedAt()));
        return reserveCancelDTO;
    }

}
