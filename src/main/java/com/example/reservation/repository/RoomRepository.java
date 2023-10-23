package com.example.reservation.repository;

import com.example.reservation.entity.CouponEntity;
import com.example.reservation.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository  extends JpaRepository<RoomEntity, Long> {
}
