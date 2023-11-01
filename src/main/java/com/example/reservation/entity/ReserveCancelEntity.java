package com.example.reservation.entity;

import com.example.reservation.dto.ReserveCancelDTO;
import com.example.reservation.dto.ReserveDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "reserve_cancel_table")
public class ReserveCancelEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long reserveId;


    public static ReserveCancelEntity toSaveEntity(ReserveCancelDTO reserveCancelDTO) {
        ReserveCancelEntity reserveCancelEntity = new ReserveCancelEntity();
        reserveCancelEntity.setMemberId(reserveCancelDTO.getMemberId());
        reserveCancelEntity.setReserveId(reserveCancelDTO.getReserveId());
        return reserveCancelEntity;
    }

}
