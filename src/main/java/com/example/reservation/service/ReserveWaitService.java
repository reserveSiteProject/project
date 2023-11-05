package com.example.reservation.service;

import com.example.reservation.dto.ReserveWaitDTO;
import com.example.reservation.entity.MemberEntity;
import com.example.reservation.entity.ReserveEntity;
import com.example.reservation.entity.ReserveWaitEntity;
import com.example.reservation.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReserveWaitService {
    private final ReserveRepository reserveRepository;
    private final MemberRepository memberRepository;
    private final ReserveWaitRepository reserveWaitRepository;

    @Transactional
    public Long save(ReserveWaitDTO reserveWaitDTO) {
        System.out.println("reserveWaitDTO = " + reserveWaitDTO);
        MemberEntity memberEntity = memberRepository.findById(reserveWaitDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        ReserveEntity reserveEntity = reserveRepository.findById(reserveWaitDTO.getReserveId()).orElseThrow(() -> new NoSuchElementException());
        ReserveWaitEntity reserveWaitEntity = ReserveWaitEntity.toSaveEntity(reserveWaitDTO, reserveEntity, memberEntity);
        return reserveWaitRepository.save(reserveWaitEntity).getId();
    }

    @Transactional
    public ReserveWaitDTO findByMemberEntityAndReserveEntity(ReserveWaitDTO reserveWaitDTO) {
        MemberEntity memberEntity = memberRepository.findById(reserveWaitDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        ReserveEntity reserveEntity = reserveRepository.findById(reserveWaitDTO.getReserveId()).orElseThrow(() -> new NoSuchElementException());
        ReserveWaitEntity reserveWaitEntity = reserveWaitRepository.findByMemberEntityAndReserveEntity(memberEntity, reserveEntity).orElseThrow(() -> new NoSuchElementException());
        return ReserveWaitDTO.toDTO(reserveWaitEntity);
    }


    @Transactional
    public ReserveWaitDTO findByReserveEntity(Long id) {
        ReserveEntity reserveEntity = reserveRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        ReserveWaitEntity reserveWaitEntity = reserveWaitRepository.findByReserveEntity(reserveEntity).orElseThrow(() -> new NoSuchElementException());
        return ReserveWaitDTO.toDTO(reserveWaitEntity);
    }

    @Transactional
    public List<ReserveWaitDTO> findAll() {
        List<ReserveWaitEntity> reserveWaitEntityList = reserveWaitRepository.findAll();
        List<ReserveWaitDTO> reserveWaitDTOList = new ArrayList<>();
        reserveWaitEntityList.forEach(reserveWait -> {
            reserveWaitDTOList.add(ReserveWaitDTO.toDTO(reserveWait));
        });
        return reserveWaitDTOList;
    }

//    public ReserveWaitDTO findById(Long reserveId) {
//
//    }
}
