package com.example.reservation.dto;

import com.example.reservation.entity.CouponEntity;
import lombok.Data;

@Data
public class CouponDTO {
    private Long id;

    private String couponName;

    private Long couponSale;

    private String startDate;

    private String endDate;

    private String serialNum;

    private int couponStatus;


    public static CouponDTO toDTO(CouponEntity couponEntity){
        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setCouponName(couponEntity.getCouponName());
        couponDTO.setCouponStatus(couponEntity.getCouponStatus());
        couponDTO.setCouponSale(couponEntity.getCouponSale());
        couponDTO.setStartDate(couponEntity.getStartDate());
        couponDTO.setEndDate(couponEntity.getEndDate());
        couponDTO.setSerialNum(couponEntity.getSerialNum());
        couponDTO.setId(couponEntity.getId());
        return couponDTO;

    }

}
