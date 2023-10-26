package com.example.reservation.repository;

import com.example.reservation.entity.ReserveStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveStatusRepository extends JpaRepository<ReserveStatusEntity, Long> {
}
