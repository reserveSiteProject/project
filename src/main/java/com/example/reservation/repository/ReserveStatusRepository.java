package com.example.reservation.repository;

import com.example.reservation.entity.MemberEntity;
import com.example.reservation.entity.ReserveEntity;
import com.example.reservation.entity.ReserveStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReserveStatusRepository extends JpaRepository<ReserveStatusEntity, Long> {
    Optional<ReserveStatusEntity> findByReserveEntity(ReserveEntity reserveEntity);


}
