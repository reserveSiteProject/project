package com.example.reservation.service;

import com.example.reservation.dto.ReviewDTO;
import com.example.reservation.dto.MemberDTO;
import com.example.reservation.entity.MemberEntity;
import com.example.reservation.entity.ReviewEntity;
import com.example.reservation.repository.MemberRepository;
import com.example.reservation.repository.boardRepositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyPagesService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    /*
    Entity, dto 변환 매서드 활성화가 되어있지 않아 주석처리 하였음
     */

    // 리뷰 목록 출력
    public List<ReviewDTO> findAll(String memberDTO) {
        MemberEntity member = MemberDTO.toEntity(memberDTO);
        List<ReviewEntity> reviewEntityList = reviewRepository.findAll(member.getId());
        List<ReviewDTO> reviewDTOList = new ArrayList<>();
        for(ReviewEntity reviewEntity : reviewEntityList){
            ReviewDTO reviewDTO = ReviewDTO.toSaveDTO(reviewEntity);
            reviewDTOList.add(reviewDTO);
        }
        return reviewDTOList;
    }
    // 리뷰 상세정보
    public ReviewDTO findById(Long id) {
        ReviewEntity reviewEntity = reviewRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        return ReviewDTO.toDTO(reviewEntity);
    }

    // 리뷰 수정 처리
    public boolean save(ReviewDTO reviewDTO) {
        ReviewEntity reviewEntity = reviewRepository.findById(reviewDTO.getId()).orElseThrow(() -> new NoSuchElementException());
        ReviewEntity review = ReviewEntity.toUpdateEntity(reviewEntity, reviewDTO);
        if(review==null){
            return false;
        }else{
            reviewRepository.save(review);
            return true;
        }
    }
    // 리뷰 삭제 처리
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }
    // 내 정보 화면 데이터 출력
    public MemberDTO findMember(String memberDTO) {
        MemberEntity memberEntity = MemberEntity.toSaveEntity(memberDTO);
        MemberEntity memberEntity1 = memberRepository.findById(memberEntity.getId()).orElseThrow(() -> new NoSuchElementException());
        return MemberDTO.toDTO(memberEntity1);
    }

    // 내 정보 수정 처리
    public boolean saveMember(MemberDTO memberDTO) {
        MemberEntity memberEntity = memberRepository.findById(memberDTO.getId()).orElseThrow(() -> new NoSuchElementException());
        MemberEntity save = memberRepository.save(memberEntity);
        if(save== null){
            return false;
        }else{
            return true;
        }
    }
    // 비밀번호 화면 출력 id 값으로 위에 매서드와 혼동주의
    public MemberDTO findByMemberId(Long id) {
        MemberEntity memberEntity = memberRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        return MemberDTO.toDTO(memberEntity);
    }
}
