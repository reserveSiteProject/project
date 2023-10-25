
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

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)

    /*
    왜 첨부파일 저장 처리가 정상적으로 되었는지:
    cascade = CascadeType.ALL 설정이 있을 때 ReviewFileEntity를 저장하는 동안 ReviewEntity에도 변경이 전파되려고 합니다.
    이때, ReviewEntity가 이미 데이터베이스에 저장되어 있어 변경이 필요 없음에도 불구하고 변경을 시도하면 문제가 발생할 수 있습니다.
    주석이 해제된 설정에서는 ReviewFileEntity만 저장하면 되기 때문에 별도의 문제 없이 정상적으로 처리됩니다.
    결론적으로, ReviewEntity와 ReviewFileEntity 사이의 관계 설정에서 cascade 옵션을 어떻게 설정하느냐에 따라 영향을 받는 것입니다.
     만약 ReviewEntity를 저장하면서 연관된 ReviewFileEntity도 함께 저장하려면 cascade 옵션을 사용하면 됩니다.
     하지만 이미 저장된 ReviewEntity에 연관된 ReviewFileEntity만 따로 저장하려면 cascade 옵션을 사용하지 않는 것이 좋습니다.
     */

    @ManyToOne(fetch = FetchType.LAZY)
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
