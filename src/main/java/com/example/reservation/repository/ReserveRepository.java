package com.example.reservation.repository;

import com.example.reservation.entity.ReserveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveRepository extends JpaRepository<ReserveEntity, Long> {
}
