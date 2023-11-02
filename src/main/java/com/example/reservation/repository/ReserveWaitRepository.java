package com.example.reservation.repository;

import com.example.reservation.entity.MemberEntity;
import com.example.reservation.entity.ReserveEntity;
import com.example.reservation.entity.ReserveWaitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReserveWaitRepository extends JpaRepository<ReserveWaitEntity, Long> {
    Optional<ReserveWaitEntity> findByMemberEntityAndReserveEntity(MemberEntity memberEntity, ReserveEntity reserveEntity);

    Optional<ReserveWaitEntity> findByReserveEntity(ReserveEntity reserveEntity);

    List<ReserveWaitEntity> findByMemberEntity(MemberEntity memberEntity);
}
