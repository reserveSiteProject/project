package com.example.reservation.entity;

import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
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
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "reserve_id")
    private ReserveEntity reserveEntity;


}