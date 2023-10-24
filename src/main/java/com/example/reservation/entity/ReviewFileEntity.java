
package com.example.reservation.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "review_File_table")
public class ReviewFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String originalFileName;

    @Column(length = 200)
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id")
    private ReviewEntity reviewEntity;
    public static ReviewFileEntity toSaveReviewFile(ReviewEntity savedEntity, String originalFilename, String storedFileName) {
        ReviewFileEntity reviewFileEntity = new ReviewFileEntity();
        reviewFileEntity.setOriginalFileName(originalFilename);
        reviewFileEntity.setStoredFileName(storedFileName);
        reviewFileEntity.setReviewEntity(savedEntity);
        return reviewFileEntity;
    }
}
