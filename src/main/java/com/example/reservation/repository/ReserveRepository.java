package com.example.reservation.repository;

import com.example.reservation.entity.MemberEntity;
import com.example.reservation.entity.ReserveEntity;
import com.example.reservation.entity.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReserveRepository extends JpaRepository<ReserveEntity, Long> {
    List<ReserveEntity> findByMemberEntity(MemberEntity memberEntity);

    Page<ReserveEntity> findByMemberEntityMemberNameContaining(String q, Pageable pageable);

    Page<ReserveEntity> findByRoomEntityRoomNameContaining(String q, Pageable pageable);

    Page<ReserveEntity> findByCheckInDate(String checkInDate, Pageable pageable);


}
