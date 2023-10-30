package com.example.reservation.repository;

import com.example.reservation.entity.ReserveWaitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveWaitRepository extends JpaRepository<ReserveWaitEntity, Long> {
}
