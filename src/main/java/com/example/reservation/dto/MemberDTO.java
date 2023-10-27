package com.example.reservation.dto;

import com.example.reservation.entity.MemberEntity;
import com.example.reservation.util.UtilClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class MemberDTO{
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String nickName;
    private String memberMobile;
    private String memberBirth;
    private String memberAddress;
    private int kakao;

    public static MemberDTO toDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberMobile(memberEntity.getMemberMobile());
        memberDTO.setMemberBirth(memberEntity.getMemberBirth());
        memberDTO.setKakao(memberEntity.getKakao());
        memberDTO.setMemberAddress(memberEntity.getMemberAddress());
        memberDTO.setNickName(memberEntity.getNickName());
        return memberDTO;
    }

}
