package com.example.reservation.service;

import com.example.reservation.dto.ReserveCancelDTO;
import com.example.reservation.entity.MemberEntity;
import com.example.reservation.entity.ReserveCancelEntity;
import com.example.reservation.entity.ReserveEntity;
import com.example.reservation.repository.ReserveCancelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReserveCancelService {
    private final ReserveCancelRepository reserveCancelRepository;

    public void save(ReserveCancelDTO reserveCancelDTO) {
        ReserveCancelEntity reserveCancelEntity = ReserveCancelEntity.toSaveEntity(reserveCancelDTO);
        reserveCancelRepository.save(reserveCancelEntity);
    }
}
