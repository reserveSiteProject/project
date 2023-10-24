package com.example.reservation.repository;

import com.example.reservation.entity.CouponEntity;
import com.example.reservation.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CouponRepository extends JpaRepository<CouponEntity, Long> {
    List<CouponEntity> findByMemberEntity(MemberEntity memberEntity);

    @Query("SELECT c FROM CouponEntity c WHERE c.memberEntity = :member AND c.couponStatus = 0")
    List<CouponEntity> findZeroValueCoupons(@Param("member") MemberEntity memberEntity);

}
