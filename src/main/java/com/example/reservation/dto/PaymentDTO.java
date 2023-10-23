package com.example.reservation.dto;

import lombok.Data;

@Data
public class PaymentDTO {
    private Long id;
    private Long reserveId;
    private Long totalPrice;
    private String createdAt;
    private String paymentBy;

}
