package com.example.reservation.service;

import com.example.reservation.dto.ReserveDTO;
import com.example.reservation.dto.ReserveStatusDTO;
import com.example.reservation.dto.RoomDTO;
import com.example.reservation.entity.MemberEntity;
import com.example.reservation.entity.ReserveEntity;
import com.example.reservation.entity.RoomEntity;
import com.example.reservation.entity.RoomFileEntity;
import com.example.reservation.repository.MemberRepository;
import com.example.reservation.repository.ReserveRepository;
import com.example.reservation.repository.RoomFileRepository;
import com.example.reservation.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReserveService {
    private final ReserveRepository reserveRepository;
    private final RoomRepository roomRepository;
    private final RoomFileRepository roomFileRepository;
    private final MemberRepository memberRepository;

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
                        .totalPrice(reserveEntity.getPaymentEntity().getTotalPrice())
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

    public void deleteById(Long id) {
        reserveRepository.deleteById(id);

    }

    @Transactional
    public ReserveDTO find(Long roomId, String checkInDate, String checkOutDate) {
        System.out.println("응애");
        RoomEntity roomEntity = roomRepository.findById(roomId).orElseThrow(() -> new NoSuchElementException());
        System.out.println("음메" + roomEntity);
        ReserveEntity reserveEntity = reserveRepository.findByCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqualAndRoomEntity(checkInDate, checkOutDate, roomEntity);
        System.out.println("여기가는지" + reserveEntity);
        return ReserveDTO.toDTO(reserveEntity);
    }
}
