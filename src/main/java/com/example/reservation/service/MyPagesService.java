package com.example.reservation.service;

import com.example.reservation.dto.ReviewDTO;
import com.example.reservation.dto.MemberDTO;
import com.example.reservation.entity.MemberEntity;
import com.example.reservation.entity.ReviewEntity;
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
    /*
    Entity, dto 변환 매서드 활성화가 되어있지 않아 주석처리 하였음
     */
//    public List<ReviewDTO> findAll(String memberDTO) {
//        MemberEntity member = MemberDTO.toEntity(memberDTO);
//        List<ReviewEntity> reviewEntityList = reviewRepository.findAll(member.getId());
//        List<ReviewDTO> reviewDTOList = new ArrayList<>();
//        for(ReviewEntity reviewEntity : reviewEntityList){
//            ReviewDTO reviewDTO = ReviewDTO.toSaveDTO(reviewEntity);
//            reviewDTOList.add(reviewDTO);
//        }
//        return reviewDTOList;
//    }

//    public ReviewDTO findById(Long id) {
//        ReviewEntity reviewEntity = reviewRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
//        return ReviewDTO.toDTO(reviewEntity);
//    }


//    public boolean save(ReviewDTO reviewDTO) {
//        ReviewEntity reviewEntity = reviewRepository.findById(reviewDTO.getId()).orElseThrow(() -> new NoSuchElementException());
//        ReviewEntity review = ReviewEntity.toUpdateEntity(reviewEntity, reviewDTO);
//        if(review==null){
//            return false;
//        }else{
//            reviewRepository.save(review);
//            return true;
//        }
//    }

    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }
}
