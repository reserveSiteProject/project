package com.example.reservation.repository;

import com.example.reservation.entity.CouponEntity;
import com.example.reservation.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponRepository extends JpaRepository<CouponEntity, Long> {
    List<CouponEntity> findByMemberEntity(MemberEntity memberEntity);
}
