package com.example.reservation;

import com.example.reservation.dto.ReviewDTO;
import com.example.reservation.repository.ReviewRepository;
import com.example.reservation.service.ReviewService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.stream.IntStream;

@SpringBootTest
@ActiveProfiles("test")
public class ReviewTest {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewRepository reviewRepository;

    // 리뷰 50개 저장하기
    private ReviewDTO newReview(int i) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setReviewTitle("title" + i);
        reviewDTO.setReviewContents("contents" + i);
        reviewDTO.setReviewWriter("writer" + i);
        reviewDTO.setReviewStar(5);
        return reviewDTO;
    }

    @Test
    @DisplayName("게시글 데이터 붓기")
    public void saveData() {
        IntStream.rangeClosed(1, 50).forEach(i -> {
            try {
                reviewService.save(newReview(i));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
