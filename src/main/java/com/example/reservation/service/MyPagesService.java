package com.example.reservation.service;

import com.example.reservation.dto.*;
import com.example.reservation.entity.*;
import com.example.reservation.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MyPagesService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final PaymentRepository paymentRepository;
    private final CouponRepository couponRepository;
    private final ReserveRepository reserveRepository;
    /*
    Entity, dto 변환 매서드 활성화가 되어있지 않아 주석처리 하였음
     */

    // 리뷰 목록 출력

    public List<ReviewDTO> findAll(MemberDTO memberDTO) {
//        MemberEntity memberEntity = MemberEntity.toUpdateEntity(memberDTO);
        MemberEntity memberEntity = memberRepository.findById(memberDTO.getId()).orElseThrow(() -> new NoSuchElementException());
        List<ReviewEntity> reviewEntityList = reviewRepository.findByMemberEntity(memberEntity);
        List<ReviewDTO> reviewDTOList = new ArrayList<>();
        for (ReviewEntity reviewEntity : reviewEntityList) {
            ReviewDTO reviewDTO = ReviewDTO.toDTO(reviewEntity);
            reviewDTOList.add(reviewDTO);
        }
        return reviewDTOList;
    }

    public List<ReviewDTO> findAllByType(MemberDTO memberDTO, String type) {
        MemberEntity memberEntity = memberRepository.findById(memberDTO.getId()).orElseThrow(() -> new NoSuchElementException());
        if (type.equals("createdAt")) {
            List<ReviewEntity> reviewEntityList = reviewRepository.findByMemberEntityOrderByCreatedAtDesc(memberEntity);
            List<ReviewDTO> reviewDTOList = new ArrayList<>();
            for (ReviewEntity reviewEntity : reviewEntityList) {
                ReviewDTO reviewDTO = ReviewDTO.toDTO(reviewEntity);
                reviewDTOList.add(reviewDTO);
                return reviewDTOList;
            }
        } else if (type.equals("hits")) {
            List<ReviewEntity> reviewEntityList = reviewRepository.findByMemberEntityOrderByHitsDesc(memberEntity);
            List<ReviewDTO> reviewDTOList = new ArrayList<>();
            for (ReviewEntity reviewEntity : reviewEntityList) {
                ReviewDTO reviewDTO = ReviewDTO.toDTO(reviewEntity);
                reviewDTOList.add(reviewDTO);
                return reviewDTOList;
            }
        }
        return null;
    }

    // 리뷰 상세정보
    public ReviewDTO findById(Long id) {
        ReviewEntity reviewEntity = reviewRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        return ReviewDTO.toDTO(reviewEntity);
    }

    // 리뷰 수정 처리
    public boolean save(ReviewDTO reviewDTO) {
        MemberEntity memberEntity = memberRepository.findById(reviewDTO.getId()).orElseThrow(() -> new NoSuchElementException());
        PaymentEntity paymentEntity = paymentRepository.findById(reviewDTO.getId()).orElseThrow(() -> new NoSuchElementException());
        ReviewEntity review = ReviewEntity.toUpdateEntity(paymentEntity, memberEntity, reviewDTO);
        if (review == null) {
            return false;
        } else {
            reviewRepository.save(review);
            return true;
        }
    }

    // 리뷰 삭제 처리
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

    // 내 정보 화면 데이터 출력
    public MemberDTO findMember(MemberDTO memberDTO) {
        MemberEntity memberEntity = memberRepository.findById(memberDTO.getId()).orElseThrow(() -> new NoSuchElementException());
        return MemberDTO.toDTO(memberEntity);
    }

    // 내 정보 수정 처리
    public boolean saveMember(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toUpdateEntity(memberDTO);
        MemberEntity save = memberRepository.save(memberEntity);
        if (save == null) {
            return false;
        } else {
            return true;
        }
    }

    // 비밀번호 화면 출력 id 값으로 위에 매서드와 혼동주의
    public MemberDTO findByMemberId(Long id) {
        MemberEntity memberEntity = memberRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        return MemberDTO.toDTO(memberEntity);
    }

    public List<CouponDTO> findCoupon(Object memberDTO) {
        MemberDTO memberDTO1 = (MemberDTO) memberDTO;
        MemberEntity memberEntity = memberRepository.findById(memberDTO1.getId()).orElseThrow(() -> new NoSuchElementException());
        List<CouponEntity> couponEntityList = couponRepository.findZeroValueCoupons(memberEntity);
        List<CouponDTO> couponDTOList = new ArrayList<>();
        for (CouponEntity couponEntity : couponEntityList) {
            CouponDTO couponDTO = CouponDTO.toDTO(couponEntity);
            couponDTOList.add(couponDTO);
        }
        return couponDTOList;
    }

    public List<ReserveDTO> findReserve(MemberDTO memberDTO) {
        MemberEntity memberEntity = memberRepository.findById(memberDTO.getId()).orElseThrow(() -> new NoSuchElementException());
        List<ReserveEntity> reserveEntityList = reserveRepository.findByMemberEntity(memberEntity);
        List<ReserveDTO> reserveDTOList = new ArrayList<>();
        for (ReserveEntity reserveEntity : reserveEntityList) {
            ReserveDTO reserveDTO = ReserveDTO.toDTO(reserveEntity);
            reserveDTOList.add(reserveDTO);
        }
        return reserveDTOList;
    }


}