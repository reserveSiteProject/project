package com.example.reservation.entity;

import com.example.reservation.dto.ReviewDTO;
import jdk.jshell.Snippet;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "review_table")
public class ReviewEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String reviewTitle;

    @Column(length = 500, nullable = false)
    private String reviewContents;

    @Column(length = 30, nullable = false)
    private String reviewWriter;

    @Column(nullable = false)
    private double reviewStar;

    @Column
    private int hits;

    @Column
    private int fileAttached;

    /* 타인원이 해당 엔티티들을 구현하고 pull request를 하고 팀장이 merge를 한후 주석을 지우면 되는 부분이다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private PaymentEntity paymentEntity;


    // 참조관계 정의
    // mappedBy: 자식 엔티티에 정의한 필드 이름
    // cascade, orphanRemoval: 부모 데이터 삭제시 자식 데이터도 삭제
    @OneToMany(mappedBy = "reviewEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ReviewFileEntity> reviewFileEntityList = new ArrayList<>();


//    public static ReviewEntity toSaveEntity(MemberEntity memberEntity, PaymentEntity paymentEntity, ReviewDTO reviewDTO) {
public static ReviewEntity toSaveEntity(ReviewDTO reviewDTO) {

    ReviewEntity reviewEntity = new ReviewEntity();
//        reviewEntity.setMemberEntity(memberEntity);
//        reviewEntity.setPaymentEntity(paymentEntity);
        reviewEntity.setReviewTitle(reviewDTO.getReviewTitle());
        reviewEntity.setReviewContents(reviewDTO.getReviewContents());
        reviewEntity.setReviewWriter(reviewDTO.getReviewWriter());
        reviewEntity.setReviewStar(reviewDTO.getReviewStar());
        reviewEntity.setFileAttached(0);
        return reviewEntity;
    }

//    public static ReviewEntity toSaveEntityWithFile(MemberEntity memberEntity, PaymentEntity paymentEntity, ReviewDTO reviewDTO) {
        public static ReviewEntity toSaveEntityWithFile(ReviewDTO reviewDTO) {

            ReviewEntity reviewEntity = new ReviewEntity();
//        reviewEntity.setMemberEntity(memberEntity);
//        reviewEntity.setPaymentEntity(paymentEntity);
        reviewEntity.setReviewTitle(reviewDTO.getReviewTitle());
        reviewEntity.setReviewContents(reviewDTO.getReviewContents());
        reviewEntity.setReviewWriter(reviewDTO.getReviewWriter());
        reviewEntity.setReviewStar(reviewDTO.getReviewStar());
        reviewEntity.setFileAttached(1);
        return reviewEntity;
    }

    // 결제테이블 참조값 추가해야함
    public static ReviewEntity toUpdateEntity(PaymentEntity paymentEntity, MemberEntity memberEntity, ReviewDTO reviewDTO) {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setId(reviewDTO.getId());
        reviewEntity.setMemberEntity(memberEntity);
        reviewEntity.setPaymentEntity(paymentEntity);
        reviewEntity.setReviewTitle(reviewDTO.getReviewTitle());
        reviewEntity.setReviewContents(reviewDTO.getReviewContents());
        reviewEntity.setReviewWriter(reviewDTO.getReviewWriter());
        reviewEntity.setReviewStar(reviewDTO.getReviewStar());
        reviewEntity.setFileAttached(reviewDTO.getFileAttached());
        return reviewEntity;
    }


}