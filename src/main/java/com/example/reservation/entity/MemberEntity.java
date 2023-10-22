package com.example.reservation.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter(AccessLevel.PRIVATE)
@Getter
@ToString
@Entity

@Table(name = "member_table")
public class MemberEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String memberEmail;

    @Column(length = 30)
    private String memberPassword;

    @Column(length = 20, nullable = false)
    private String memberName;

    @Column(length = 30, nullable = false, unique = true)
    private String nickName;

    @Column(length = 20, nullable = false)
    private String memberMobile;

    @Column(length = 40)
    private String memberBirth;

    @Column(length = 50)
    private String memberAddress;

    @Column(nullable = false)
    private int kakao;
}
