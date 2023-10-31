package com.example.reservation.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
//@SuperBuilder
public class PaymentDTO {
    private Long id;
    private Long reserveId;
    private Long memberId;
    private Long totalPrice;
    private String createdAt;
    private String paymentBy;

}