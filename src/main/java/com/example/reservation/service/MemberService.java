package com.example.reservation.service;

import com.example.reservation.dto.MemberDTO;
import com.example.reservation.entity.MemberEntity;
import com.example.reservation.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberService {
    public final MemberRepository memberRepository;

    public Long save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toSaveEntity(memberDTO);
        return memberRepository.save(memberEntity).getId();
    }

    public MemberDTO duplicateCheck(String memberEmail) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail).orElseThrow(() -> new NoSuchElementException());
        return MemberDTO.toDTO(memberEntity);
    }

    public MemberDTO nicknameCheck(String nickName) {
        MemberEntity memberEntity = memberRepository.findByNickName(nickName).orElseThrow(() -> new NoSuchElementException());
        return MemberDTO.toDTO(memberEntity);
    }

    public Page<MemberDTO> findAll(int page, String q) {
        page = page - 1;
        int pageLimit = 5;
        Page<MemberEntity> memberEntities = null;
        // 검색인지 구분
        if (q.equals("")) {
            // 일반 페이징
            memberEntities = memberRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        } else {
            // 회원 이름 검색 페이징
            memberEntities = memberRepository.findByMemberNameContaining(q, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        }


        Page<MemberDTO> memberList = memberEntities.map(memberEntity ->
                MemberDTO.builder()
                        .id(memberEntity.getId())
                        .memberEmail(memberEntity.getMemberEmail())
                        .memberName(memberEntity.getMemberName())
                        .nickName(memberEntity.getNickName())
                        .build());
        return memberList;
    }

    public MemberDTO login(MemberDTO memberDTO) {
        MemberEntity memberEntity = memberRepository.findByMemberEmailAndMemberPassword(memberDTO.getMemberEmail(), memberDTO.getMemberPassword()).orElseThrow(() -> new NoSuchElementException());
        return MemberDTO.toDTO(memberEntity);
    }

    public MemberDTO findByMemberEmail(String memberEmail) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail).orElseThrow(() -> new NoSuchElementException());
        return MemberDTO.toDTO(memberEntity);
    }

    public void update(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toUpdateEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public MemberDTO findById(Long memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());
        return MemberDTO.toDTO(memberEntity);
    }

    public MemberDTO findByMemberMobile(String memberMobile) {
        MemberEntity memberEntity = memberRepository.findByMemberMobile(memberMobile).orElseThrow(() -> new NoSuchElementException());
        return MemberDTO.toDTO(memberEntity);
    }
}
