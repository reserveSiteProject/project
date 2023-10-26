package com.example.reservation.dto;

import com.example.reservation.entity.ReserveStatusEntity;
import lombok.Data;

@Data
public class ReserveStatusDTO {
    private Long id;
    private int status;
    private Long reserveId;


    public ReserveStatusDTO toDTO(ReserveStatusEntity reserveStatusEntity){
        ReserveStatusDTO reserveStatusDTO = new ReserveStatusDTO();
        reserveStatusDTO.setId(reserveStatusEntity.getId());
        reserveStatusDTO.setStatus(reserveStatusEntity.getStatus());
        reserveStatusDTO.setReserveId(reserveStatusEntity.getReserveEntity().getId());
        return reserveStatusDTO;
    }
}
