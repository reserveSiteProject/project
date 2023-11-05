package com.example.reservation.dto;

import com.example.reservation.entity.NoticeEntity;
import com.example.reservation.util.UtilClass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;

@Getter
@Setter
@ToString
public class NoticeDTO {
    private Long id;
    private String noticeWriter;
    private String noticeContents;
    private String noticeTitle;
    private String createdAt;

    public static NoticeDTO toDTO(NoticeEntity noticeEntity) throws IOException {
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setId(noticeEntity.getId());
        noticeDTO.setNoticeWriter(noticeEntity.getNoticeWriter());
        noticeDTO.setNoticeContents(noticeEntity.getNoticeContents());
        noticeDTO.setNoticeTitle(noticeEntity.getNoticeTitle());
        noticeDTO.setCreatedAt(UtilClass.dateTimeFormat(noticeEntity.getCreatedAt()));
        return noticeDTO;
    }

}
