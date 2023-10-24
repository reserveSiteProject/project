package com.example.reservation.repository;

import com.example.reservation.entity.RoomEntity;
import com.example.reservation.entity.RoomFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomFileRepository extends JpaRepository<RoomFileEntity, Long> {
    List<RoomFileEntity> findByRoomEntity(RoomEntity roomEntity);
}
