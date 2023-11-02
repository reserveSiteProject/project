package com.example.reservation.repository;

import com.example.reservation.entity.MemberEntity;
import com.example.reservation.entity.ReserveCancelEntity;
import com.example.reservation.entity.ReserveStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReserveCancelRepository extends JpaRepository<ReserveCancelEntity, Long> {
    List<ReserveCancelEntity> findByMemberId(Long memberId);
}
