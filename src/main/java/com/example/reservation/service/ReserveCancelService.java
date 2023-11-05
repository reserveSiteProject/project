package com.example.reservation.service;

import com.example.reservation.dto.ReserveCancelDTO;
import com.example.reservation.entity.ReserveCancelEntity;
import com.example.reservation.repository.ReserveCancelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReserveCancelService {
    private final ReserveCancelRepository reserveCancelRepository;

    public void save(ReserveCancelDTO reserveCancelDTO) {
        ReserveCancelEntity reserveCancelEntity = ReserveCancelEntity.toSaveEntity(reserveCancelDTO);
        reserveCancelRepository.save(reserveCancelEntity);
    }

    public List<ReserveCancelDTO> findAll() {
        List<ReserveCancelEntity> reserveCancelEntityList = reserveCancelRepository.findAll();
        List<ReserveCancelDTO> reserveCancelDTOList = new ArrayList<>();
        reserveCancelEntityList.forEach(reserveCancel -> {
            reserveCancelDTOList.add(ReserveCancelDTO.toDTO(reserveCancel));
        });
        return reserveCancelDTOList;
    }
}
