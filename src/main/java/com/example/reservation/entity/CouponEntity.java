package com.example.reservation.entity;

import com.example.reservation.dto.CouponDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Setter(AccessLevel.PRIVATE)
@Getter
@ToString
@Entity



@Table(name = "coupon_table")
public class CouponEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String couponName;

    @Column(nullable = false)
    private Long couponSale;

    @Column(length = 20, nullable = false)
    private String startDate;

    @Column(length = 20, nullable = false)
    private String endDate;

    @Column(nullable = false)
    private int couponStatus;

    @Column(nullable = false)
    private String serialNum;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity; // 이것을 부모로서 참조하겠다.

    public static CouponEntity toSaveEntity(CouponDTO couponDTO, MemberEntity memberEntity){
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setMemberEntity(memberEntity);
        couponEntity.setCouponName(couponDTO.getCouponName());
        couponEntity.setStartDate(couponDTO.getStartDate());
        couponEntity.setEndDate(couponDTO.getEndDate());
        couponEntity.setCouponSale(couponDTO.getCouponSale());
        couponEntity.setSerialNum(couponDTO.getSerialNum());
        couponEntity.setCouponStatus(couponDTO.getCouponStatus());
        return couponEntity;
    }


}
