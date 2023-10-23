package com.example.reservation.service;

import com.example.reservation.dto.CouponDTO;
import com.example.reservation.dto.MemberDTO;
import com.example.reservation.entity.CouponEntity;
import com.example.reservation.entity.MemberEntity;
import com.example.reservation.repository.CouponRepository;
import com.example.reservation.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponService {
private final CouponRepository couponRepository;
private final MemberRepository memberRepository;

    public MemberEntity findById(Long memberId) {
      MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(()-> new NoSuchElementException());
        return memberEntity;
    }

    public void save(CouponDTO couponDTO, MemberEntity memberEntity){
       CouponEntity couponEntity = CouponEntity.toSaveEntity(couponDTO, memberEntity);
       couponRepository.save(couponEntity);
    }
}
