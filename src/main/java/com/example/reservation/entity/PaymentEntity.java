package com.example.reservation.entity;

import com.example.reservation.dto.PaymentDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@ToString
@Getter
@Table(name="payment_table")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long totalPrice;
    @Column
    private String paymentAt;
    @Column(nullable = false)
    private String paymentBy;

    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "reserve_id")
    private ReserveEntity reserveEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "paymentEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ReviewEntity> reviewEntityList = new ArrayList<>();


    public static PaymentEntity toEntity(PaymentDTO paymentDTO, ReserveEntity reserveEntity, MemberEntity memberEntity) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setMemberEntity(memberEntity);
        paymentEntity.setReserveEntity(reserveEntity);
        paymentEntity.setTotalPrice(paymentDTO.getTotalPrice());
        paymentEntity.setPaymentAt(paymentDTO.getCreatedAt());
        paymentEntity.setPaymentBy(paymentDTO.getPaymentBy());
        return paymentEntity;
    }
}