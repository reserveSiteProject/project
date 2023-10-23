package com.example.reservation.repository;

import com.example.reservation.entity.RoomFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomFileRepository extends JpaRepository<RoomFileEntity, Long> {
}
