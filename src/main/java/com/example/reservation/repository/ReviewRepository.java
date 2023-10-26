package com.example.reservation.repository;

import com.example.reservation.entity.MemberEntity;
import com.example.reservation.entity.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
//    @Query("SELECT r FROM ReviewEntity r WHERE r.memberEntity.id = :memberId")
//    List<ReviewEntity> findByMemberId(@Param("memberId") Long memberId);

    // select * from board_table where board_title like '%q%'
    List<ReviewEntity> findByReviewTitleContaining(String q);

    // select * from board_table where board_writer like '%q%'
    List<ReviewEntity> findByReviewWriterContaining(String q);

    // 제목으로 검색한 결과를 Page 객체로 리턴
    Page<ReviewEntity> findByReviewTitleContaining(String q, Pageable pageable);
    // 작성자로 검색한 결과를 Page 객체로 리턴
    Page<ReviewEntity> findByReviewWriterContaining(String q, Pageable pageable);

    List<ReviewEntity> findByMemberEntity(MemberEntity memberEntity);

    List<ReviewEntity> findByMemberEntityOrderByCreatedAtDesc(MemberEntity memberEntity);
    List<ReviewEntity> findByMemberEntityOrderByHitsDesc(MemberEntity memberEntity);

    @Modifying
    @Query(value = "update ReviewEntity b set b.hits = b.hits + 1 where b.id=:id")
    void increaseHits(@Param("id") Long id);
}
