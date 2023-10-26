package com.example.reservation.entity;

import com.example.reservation.dto.ReserveStatusDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter(AccessLevel.PRIVATE)
@Getter
@ToString
@Entity

@Table(name = "reserve_status_table")
public class ReserveStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int status;

    @OneToOne(fetch = FetchType.LAZY) // 지금 클래스 기준
    @JoinColumn(name="reserve_id")
    private ReserveEntity reserveEntity;



    public ReserveStatusEntity toSaveEntity(ReserveStatusDTO reserveStatusDTO, ReserveEntity reserveEntity){
        ReserveStatusEntity reserveStatusEntity = new ReserveStatusEntity();
        reserveStatusEntity.setStatus(reserveStatusDTO.getStatus());
        reserveStatusEntity.setReserveEntity(reserveEntity);
        return reserveStatusEntity;
    }

}
