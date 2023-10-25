package com.example.reservation.entity;


import com.example.reservation.dto.ReserveDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter(AccessLevel.PRIVATE)
@Getter
@ToString
@Entity

@Table(name = "reserve_table")
public class ReserveEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String checkInDate;

    @Column(nullable = false, length = 40)
    private String checkOutDate;

    @Column(nullable = false)
    private int persons;

    @OneToMany(mappedBy = "reserveEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PaymentEntity> paymentEntityList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY) // 지금 클래스 기준
    @JoinColumn(name="member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY) // 지금 클래스 기준
    @JoinColumn(name="room_id")
    private RoomEntity roomEntity;

    public static ReserveEntity toSaveEntity(ReserveDTO reserveDTO, MemberEntity memberEntity, RoomEntity roomEntity) {
        ReserveEntity reserveEntity = new ReserveEntity();
        reserveEntity.setMemberEntity(memberEntity);
        reserveEntity.setRoomEntity(roomEntity);
        reserveEntity.setCheckInDate(reserveDTO.getCheckInDate());
        reserveEntity.setCheckOutDate(reserveDTO.getCheckOutDate());
        reserveEntity.setPersons(reserveDTO.getPersons());
        return reserveEntity;
    }



}

