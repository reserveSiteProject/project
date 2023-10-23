package com.example.reservation.dto;

import com.example.reservation.entity.CouponEntity;
import lombok.Data;

@Data
public class CouponDTO {
    private Long id;

    private Long memberId;

    private String couponName;

    private Long couponSale;

    private String startDate;

    private String endDate;

    private String serialNum;

    private int couponStatus;


    public static CouponDTO toDTO(CouponEntity couponEntity){
        CouponDTO couponDTO = new CouponDTO();


        return couponDTO;
    }

}








