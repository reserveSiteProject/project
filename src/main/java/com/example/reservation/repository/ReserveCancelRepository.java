package com.example.reservation.repository;

import com.example.reservation.entity.ReserveCancelEntity;
import com.example.reservation.entity.ReserveStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveCancelRepository extends JpaRepository<ReserveCancelEntity, Long> {
}
