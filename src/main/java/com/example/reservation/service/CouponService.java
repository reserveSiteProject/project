package com.example.reservation.service;

import com.example.reservation.dto.CouponDTO;
import com.example.reservation.entity.CouponEntity;
import com.example.reservation.entity.MemberEntity;
import com.example.reservation.repository.CouponRepository;
import com.example.reservation.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    public List<CouponDTO> findByCoupon(Long id) {
        MemberEntity memberEntity = memberRepository.findById(id).orElseThrow(()-> new NoSuchElementException());
        List<CouponDTO> couponDTOList = new ArrayList<>();
        for (CouponEntity couponEntity : couponRepository.findByMemberEntity(memberEntity)){
            couponDTOList.add(CouponDTO.toDTO(couponEntity));
        }
        return couponDTOList;
    }

    public CouponDTO findByCouponId(Long id){
        CouponEntity couponEntity = couponRepository.findById(id).orElseThrow(()-> new NoSuchElementException());
        return CouponDTO.toDTO(couponEntity);
    }
}
