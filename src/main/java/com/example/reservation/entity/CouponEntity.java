package com.example.reservation.entity;

import com.example.reservation.dto.CouponDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private String serialNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity; // 이것을 부모로서 참조하겠다.





    public static CouponEntity toSaveEntity(CouponDTO couponDTO){
        CouponEntity couponEntity = new CouponEntity();




        return couponEntity;
    }









}
