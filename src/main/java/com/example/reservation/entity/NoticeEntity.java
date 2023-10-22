package com.example.reservation.entity;

import com.example.reservation.dto.NoticeDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "notice_table")
public class NoticeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String noticeWriter;

    @Column(length = 500)
    private String noticeContents;

    @Column(length = 50)
    private String noticeTitle;

    public static NoticeEntity toSaveEntity(NoticeDTO noticeDTO) {
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNoticeTitle(noticeDTO.getNoticeTitle());
        noticeEntity.setNoticeContents(noticeDTO.getNoticeContents());
        noticeEntity.setNoticeWriter(noticeEntity.getNoticeWriter());
        return noticeEntity;
    }
}
