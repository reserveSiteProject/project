package com.example.reservation.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "notice_table")
public class NoticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String noticeWriter;

    @Column(length = 500)
    private String noticeContents;

    @Column(length = 50)
    private String noticeTitle;
}
