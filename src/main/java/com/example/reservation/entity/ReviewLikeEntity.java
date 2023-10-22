package com.example.reservation.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "review_like_table")
public class ReviewLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     /*타팀원의 엔티티들이 구현되고 Merge를 하고 주석을 지우면 되는 부분이다.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

      */


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private ReviewEntity reviewEntity;

     /*타팀원의 엔티티들이 구현되고 Merge를 하고 주석을 지우면 되는 부분

    public static ReviewLikeEntity toReviewLikeEntity(MemberEntity memberEntity, ReviewEntity reviewEntity) {
        ReviewLikeEntity reviewlikeEntity = new ReviewLikeEntity();
        reviewlikeEntity.setMemberEntity(memberEntity);
        reviewlikeEntity.setReviewEntity(reviewEntity);
        return reviewlikeEntity;
    }
      */


}
