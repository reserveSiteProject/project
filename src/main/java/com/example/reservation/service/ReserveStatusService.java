package com.example.reservation.service;

import com.example.reservation.dto.ReserveStatusDTO;
import com.example.reservation.entity.ReserveEntity;
import com.example.reservation.entity.ReserveStatusEntity;
import com.example.reservation.repository.ReserveRepository;
import com.example.reservation.repository.ReserveStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReserveStatusService {
    private final ReserveRepository reserveRepository;
    private final ReserveStatusRepository reserveStatusRepository;
    public ReserveStatusDTO findByReserveEntity(Long id) {
        ReserveEntity reserveEntity = reserveRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        ReserveStatusEntity reserveStatusEntity = reserveStatusRepository.findByReserveEntity(reserveEntity).orElseThrow(() -> new NoSuchElementException());
        return ReserveStatusDTO.toDTO(reserveStatusEntity);
    }

    public void save(ReserveStatusDTO reserveStatusDTO) {
        System.out.println("reserveStatusDTO = " + reserveStatusDTO);
        ReserveEntity reserveEntity = reserveRepository.findById(reserveStatusDTO.getReserveId()).orElseThrow(() -> new NoSuchElementException());
        System.out.println("reserveEntity.getId() = " + reserveEntity.getId());
        ReserveStatusEntity reserveStatusEntity = ReserveStatusEntity.toUpdateEntity(reserveStatusDTO, reserveEntity);
        System.out.println("reserveStatusEntity = " + reserveStatusEntity);
        reserveStatusRepository.save(reserveStatusEntity);
    }
}
