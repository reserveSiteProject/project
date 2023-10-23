package com.example.reservation.service.testBoardServices;

import com.example.reservation.dto.ReviewDTO;
import com.example.reservation.entity.ReviewEntity;
import com.example.reservation.repository.ReviewFileRepository;
import com.example.reservation.repository.ReviewRepository;
import com.example.reservation.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Profile("test")
public class TestReviewService extends ReviewService {
    // 이 서비스는 테스트용이므로, 실제로 레포지토리를 사용하여 데이터를 저장하지 않는다.

    @Autowired
    public TestReviewService(ReviewRepository reviewRepository, ReviewFileRepository reviewFileRepository) {
        super(reviewRepository, reviewFileRepository);
    }
    @Override
    public Long save(ReviewDTO reviewDTO) throws IOException {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReviewTitle(reviewDTO.getReviewTitle());
        reviewEntity.setReviewContents(reviewDTO.getReviewContents());
        reviewEntity.setReviewWriter(reviewDTO.getReviewWriter());
        reviewEntity.setReviewStar(reviewDTO.getReviewStar());
        reviewEntity.setFileAttached(reviewDTO.getReviewFile().isEmpty() ? 0 : 1);
        return 1L;  // 테스트용 ID 값 반환
    }
}
