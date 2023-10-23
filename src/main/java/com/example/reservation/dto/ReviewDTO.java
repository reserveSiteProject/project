package com.example.reservation.dto;

import com.example.reservation.entity.ReviewEntity;
import com.example.reservation.entity.ReviewFileEntity;
import com.example.reservation.util.UtilClass;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewDTO {
    private Long id;
    private Long paymentId;
    private String reviewTitle;
    private String reviewContents;
    private String reviewWriter;
    private double reviewStar;
    private String createdAt;
    private String updatedAt;
    private int hits;

    private List<MultipartFile> reviewFile = new ArrayList<>();
    private int fileAttached;
    private List<String> originalFileName = new ArrayList<>();
    private List<String> storedFileName = new ArrayList<>();

    public static ReviewDTO toDTO(ReviewEntity reviewEntity) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(reviewEntity.getId());
        reviewDTO.setReviewTitle(reviewEntity.getReviewTitle());
        reviewDTO.setReviewContents(reviewEntity.getReviewContents());
        reviewDTO.setReviewWriter(reviewEntity.getReviewWriter());
        reviewDTO.setReviewStar(reviewEntity.getReviewStar());
        reviewDTO.setHits(reviewEntity.getHits());
        reviewDTO.setCreatedAt(UtilClass.dateTimeFormat(reviewEntity.getCreatedAt()));
        reviewDTO.setUpdatedAt(UtilClass.dateTimeFormat(reviewEntity.getUpdatedAt()));
        if(reviewEntity.getFileAttached() == 1) {
            for (ReviewFileEntity reviewFileEntity : reviewEntity.getReviewFileEntityList()) {
                reviewDTO.getOriginalFileName().add(reviewFileEntity.getOriginalFileName());
                reviewDTO.getStoredFileName().add(reviewFileEntity.getStoredFileName());
            }
            reviewDTO.setFileAttached(1);
        }else {
            reviewDTO.setFileAttached(0);
        }
        return reviewDTO;
    }
}

