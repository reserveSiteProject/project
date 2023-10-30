package com.example.reservation.service;

import com.example.reservation.dto.ReserveWaitDTO;
import com.example.reservation.entity.MemberEntity;
import com.example.reservation.entity.ReserveEntity;
import com.example.reservation.entity.ReserveWaitEntity;
import com.example.reservation.entity.RoomEntity;
import com.example.reservation.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReserveWaitService {
    private final ReserveRepository reserveRepository;
    private final MemberRepository memberRepository;
    private final ReserveWaitRepository reserveWaitRepository;

    @Transactional
    public Long save(ReserveWaitDTO reserveWaitDTO) {
        MemberEntity memberEntity = memberRepository.findById(reserveWaitDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        ReserveEntity reserveEntity = reserveRepository.findById(reserveWaitDTO.getReserveId()).orElseThrow(() -> new NoSuchElementException());
        ReserveWaitEntity reserveWaitEntity = ReserveWaitEntity.toSaveEntity(reserveWaitDTO, reserveEntity, memberEntity);
        return reserveWaitRepository.save(reserveWaitEntity).getId();
    }

    public ReserveWaitEntity findById(Long id) {
        ReserveWaitEntity reserveWaitEntity = reserveWaitRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        return reserveWaitEntity;
    }
}
