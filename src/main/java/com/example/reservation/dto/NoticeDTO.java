package com.example.reservation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoticeDTO {
    private Long id;
    private String noticeWriter;
    private String noticeContents;
    private String noticeTitle;
}
