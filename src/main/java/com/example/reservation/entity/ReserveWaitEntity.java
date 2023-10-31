package com.example.reservation.entity;

import com.example.reservation.dto.ReserveWaitDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter(AccessLevel.PRIVATE)
@Getter
@Entity

@Table(name = "reserve_wait_table")
public class ReserveWaitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int persons;


    @OneToOne(fetch = FetchType.LAZY) // 지금 클래스 기준
    @JoinColumn(name="reserve_id")
    private ReserveEntity reserveEntity;

    @OneToOne(fetch = FetchType.LAZY) // 지금 클래스 기준
    @JoinColumn(name="member_id")
    private MemberEntity memberEntity;

    public static ReserveWaitEntity toSaveEntity(ReserveWaitDTO reserveWaitDTO, ReserveEntity reserveEntity, MemberEntity memberEntity){
        ReserveWaitEntity reserveWaitEntity = new ReserveWaitEntity();
        reserveWaitEntity.setPersons(reserveWaitDTO.getPersons());
        reserveWaitEntity.setReserveEntity(reserveEntity);
        reserveWaitEntity.setMemberEntity(memberEntity);
        return reserveWaitEntity;
    }


}

