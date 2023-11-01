package com.example.reservation.dto;

import com.example.reservation.entity.ReserveWaitEntity;
import lombok.Data;

@Data
public class ReserveWaitDTO {
    private Long id;
    private Long memberId;
    private Long reserveId;
    private int persons;
    private String checkInDate;
    private String checkOutDate;
    private String memberMobile;
    private String memberName;

    public static ReserveWaitDTO toDTO(ReserveWaitEntity reserveWaitEntity){
        ReserveWaitDTO reserveWaitDTO = new ReserveWaitDTO();
        reserveWaitDTO.setId(reserveWaitEntity.getId());
        reserveWaitDTO.setMemberId(reserveWaitEntity.getMemberEntity().getId());
        reserveWaitDTO.setReserveId(reserveWaitEntity.getReserveEntity().getId());
        reserveWaitDTO.setPersons(reserveWaitEntity.getPersons());
        reserveWaitDTO.setMemberMobile(reserveWaitEntity.getMemberEntity().getMemberMobile());
        reserveWaitDTO.setMemberName(reserveWaitEntity.getMemberEntity().getMemberName());
        return reserveWaitDTO;
    }
}
