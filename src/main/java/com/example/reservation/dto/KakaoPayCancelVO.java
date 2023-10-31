package com.example.reservation.dto;

import lombok.Data;

import java.util.Date;

@Data
public class KakaoPayCancelVO {
    private String aid, tid, cid, status;
    private String partner_order_id, partner_user_id, payment_method_type;
    private String item_name, item_code, payload;
    private Date created_at, approved_at, canceled_at;
    private int quantity;
    private AmountVO amount;
    private CardVO card_info;
}
