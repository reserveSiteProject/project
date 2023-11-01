package com.example.reservation.service;

import com.example.reservation.dto.ReserveDTO;
import com.example.reservation.dto.ReserveStatusDTO;
import com.example.reservation.dto.ReserveWaitDTO;
import com.example.reservation.dto.RoomDTO;
import com.example.reservation.entity.*;
import com.example.reservation.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ReserveService {
    private final ReserveRepository reserveRepository;
    private final RoomRepository roomRepository;
    private final RoomFileRepository roomFileRepository;
    private final MemberRepository memberRepository;
    private final ReserveStatusRepository reserveStatusRepository;



    @Transactional
    public Page<ReserveDTO> findAll(int page, String q, String type, int list, String checkInDate) {
        System.out.println(page + q + type);
        page = page - 1;
        int pageLimit = list;
        Page<ReserveEntity> reserveEntities = null;

        if (!checkInDate.equals("")) {
            reserveEntities = reserveRepository.findByCheckInDate(checkInDate, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        } else if (checkInDate.equals("")) {
            if (q.equals("")) {
                reserveEntities = reserveRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
            } else {
                if (type.equals("memberName")) {
                    reserveEntities = reserveRepository.findByMemberEntityMemberNameContaining(q, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
                } else if (type.equals("roomName")) {
                    reserveEntities = reserveRepository.findByRoomEntityRoomNameContaining(q, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
                }
            }
        }
        Page<ReserveDTO> reserveList = reserveEntities.map(reserveEntity ->
                ReserveDTO.builder()
                        .id(reserveEntity.getId())
                        .memberId(reserveEntity.getMemberEntity().getId())
                        .memberName(reserveEntity.getMemberEntity().getMemberName())
                        .roomId(reserveEntity.getRoomEntity().getId())
                        .roomName(reserveEntity.getRoomEntity().getRoomName())
                        .checkInDate(reserveEntity.getCheckInDate())
                        .checkOutDate(reserveEntity.getCheckOutDate())
                        .totalPrice(reserveEntity.getTotalPrice())
                        .persons(reserveEntity.getPersons())
                        .status(reserveEntity.getReserveStatusEntity().getStatus())
                        .build());
        return reserveList;
    }



    public Long save(ReserveDTO reserveDTO) {
        MemberEntity memberEntity = memberRepository.findById(reserveDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        RoomEntity roomEntity = roomRepository.findById(reserveDTO.getRoomId()).orElseThrow(() -> new NoSuchElementException());
        ReserveEntity reserveEntity = ReserveEntity.toSaveEntity(reserveDTO, memberEntity, roomEntity);
        return reserveRepository.save(reserveEntity).getId();
    }


    @Transactional
    public ReserveDTO find(Long roomId, String checkInDate, String checkOutDate) {
        String checkInDatePattern = "%" + checkInDate + "%";
        String checkOutDatePattern = "%" + checkOutDate + "%";
        RoomEntity roomEntity = roomRepository.findById(roomId).orElseThrow(() -> new NoSuchElementException());
        ReserveEntity reserveEntity = reserveRepository.findDate(checkInDate, checkOutDate, roomEntity, checkInDatePattern, checkOutDatePattern);
        return ReserveDTO.toDTO(reserveEntity);

    }

    @Transactional
    public void update(Long id) {
        ReserveEntity reserveEntity = reserveRepository.findById(id).get();
        ReserveStatusDTO reserveStatusDTO = ReserveStatusDTO.toDTO(reserveEntity.getReserveStatusEntity());


        System.out.println(ReserveDTO.toDTO(reserveEntity));
        System.out.println(reserveStatusDTO);


        reserveStatusDTO.setStatus(3);


        ReserveStatusEntity reserveStatusEntity = ReserveStatusEntity.toUpdateEntity(reserveStatusDTO, reserveEntity);


        System.out.println(reserveStatusDTO);
        reserveStatusRepository.save(reserveStatusEntity);
    }


    @Transactional
    public ReserveDTO findByIdAndMemberEntity(Long id, Long memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());
        return ReserveDTO.toDTO(reserveRepository.findByIdAndMemberEntity(id, memberEntity)
                .orElseThrow(() -> new NoSuchElementException()));

    }


    @Transactional
    public ReserveDTO findById(Long reserveId) {
        System.out.println("reserveId = " + reserveId);
        return ReserveDTO.toDTO(reserveRepository.findById(reserveId).orElseThrow(() -> new NoSuchElementException()));
    }

    public void delete(Long id) {
        System.out.println("idazzzz = " + id);
        reserveRepository.deleteById(id);
    }
}
