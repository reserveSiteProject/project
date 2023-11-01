package com.example.reservation.repository;

import com.example.reservation.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //풀퀘 왜 안떠
    Optional<MemberEntity> findByMemberEmail(String memberEmail);

    Optional<MemberEntity> findByNickName(String nickName);

    Page<MemberEntity> findByMemberNameContaining(String q, Pageable pageable);

    Optional<MemberEntity> findByMemberEmailAndMemberPassword(String memberEmail, String memberPassword);

    Optional<MemberEntity> findByMemberMobile(String memberMobile);
}
