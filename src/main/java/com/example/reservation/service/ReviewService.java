package com.example.reservation.service;

import com.example.reservation.dto.ReviewDTO;
import com.example.reservation.entity.MemberEntity;
import com.example.reservation.entity.PaymentEntity;
import com.example.reservation.entity.ReviewEntity;
import com.example.reservation.entity.ReviewFileEntity;
import com.example.reservation.repository.MemberRepository;
import com.example.reservation.repository.PaymentRepository;
import com.example.reservation.repository.ReviewFileRepository;
import com.example.reservation.repository.ReviewRepository;
import com.example.reservation.util.UtilClass;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.boot.Banner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Profile("!test")
public class ReviewService {


    private final MemberRepository memberRepository;
    private final PaymentRepository paymentRepository;

    private final ReviewRepository reviewRepository;
    private final ReviewFileRepository reviewFileRepository;

    public Long save(ReviewDTO reviewDTO) throws IOException {
        if (reviewDTO.getReviewFile().get(0).isEmpty() && reviewDTO.getReviewFile() != null) {
            // 첨부파일 없음
            MemberEntity memberEntity = memberRepository.findById(reviewDTO.getId()).orElseThrow(() -> new NoSuchElementException());
            PaymentEntity paymentEntity = paymentRepository.findById(reviewDTO.getPaymentId()).orElseThrow(()->new NoSuchElementException());
            ReviewEntity reviewEntity = ReviewEntity.toSaveEntity(memberEntity,paymentEntity,reviewDTO);



            return reviewRepository.save(reviewEntity).getId();
        } else {
            // 첨부파일 있음
            MemberEntity memberEntity = memberRepository.findById(reviewDTO.getId()).orElseThrow(() -> new NoSuchElementException());
            PaymentEntity paymentEntity = paymentRepository.findById(reviewDTO.getPaymentId()).orElseThrow(()->new NoSuchElementException());
            ReviewEntity reviewEntity = ReviewEntity.toSaveEntityWithFile(memberEntity,paymentEntity,reviewDTO);

            // 게시글 저장처리 후 저장한 엔티티 가져옴
            ReviewEntity savedEntity = reviewRepository.save(reviewEntity);
            // 파일 이름 처리, 파일 로컬에 저장 등
            // DTO에 담긴 파일리스트 꺼내기
            for (MultipartFile reviewFile: reviewDTO.getReviewFile()) {
                // 업로드한 파일 이름
                String originalFilename = reviewFile.getOriginalFilename();
                // 저장용 파일 이름
                String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
                // 저장경로+파일이름 준비
                String savePath = "C:\\final_img\\" + storedFileName;
                // 파일 폴더에 저장
                reviewFile.transferTo(new File(savePath));
                // 파일 정보 board_file_table에 저장
                // 파일 정보 저장을 위한 BoardFileEntity 생성
                ReviewFileEntity reviewFileEntity =
                        ReviewFileEntity.toSaveReviewFile(savedEntity, originalFilename, storedFileName);
                reviewFileRepository.save(reviewFileEntity);
            }
            return savedEntity.getId();
        }
    }

    public Page<ReviewDTO> findAll(int page, String type, String q) {
        page = page - 1;
        int pageLimit = 5;
        Page<ReviewEntity> reviewEntities = null;
        // 검색인지 구분
        if (q.equals("")) {
            // 일반 페이징
            reviewEntities = reviewRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        } else {
            if (type.equals("reviewTitle")) {
                reviewEntities = reviewRepository.findByReviewTitleContaining(q, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
            } else if (type.equals("reviewWriter")) {
                reviewEntities = reviewRepository.findByReviewWriterContaining(q, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
            }
        }
        Page<ReviewDTO> reviewList = reviewEntities.map(reviewEntity ->
                ReviewDTO.builder()
                        .id(reviewEntity.getId())
                        .reviewTitle(reviewEntity.getReviewTitle())
                        .reviewWriter(reviewEntity.getReviewWriter())
                        .reviewStar(reviewEntity.getReviewStar())
                        .hits(reviewEntity.getHits())
                        .createdAt(UtilClass.dateTimeFormat(reviewEntity.getCreatedAt()))
                        .build());
        return reviewList;
    }

    @Transactional
    public ReviewDTO findById(Long id) {
        ReviewEntity reviewEntity = reviewRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
//        Hibernate.initialize(reviewEntity.getReviewFileEntityList());  // 지연 로딩된 컬렉션 초기화
        System.out.println("reviewEntity = " + reviewEntity);
        return ReviewDTO.toDTO(reviewEntity);

    }
}
