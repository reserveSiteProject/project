package com.example.reservation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewLikeDTO {
    private Long id;
    private Long memberId;
    private Long reviewId;
}
