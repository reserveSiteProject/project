package com.example.reservation.repository;

import com.example.reservation.entity.MemberEntity;
import com.example.reservation.entity.ReserveEntity;
import com.example.reservation.entity.ReviewEntity;
import com.example.reservation.entity.RoomEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReserveRepository extends JpaRepository<ReserveEntity, Long> {
    List<ReserveEntity> findAllByMemberEntity(MemberEntity memberEntity);

    Page<ReserveEntity> findByMemberEntityMemberNameContaining(String q, Pageable pageable);

    Page<ReserveEntity> findByRoomEntityRoomNameContaining(String q, Pageable pageable);

    Page<ReserveEntity> findByCheckInDate(String checkInDate, Pageable pageable);

    @Query("SELECT r FROM ReserveEntity r " +
            "WHERE " +
            "((r.checkInDate <= :checkOutDate AND r.checkOutDate >= :checkInDate AND r.roomEntity = :roomEntity) " +
            "OR " +
            "(r.checkInDate LIKE :checkInDatePattern AND r.checkOutDate LIKE :checkOutDatePattern AND r.roomEntity = :roomEntity))")
            ReserveEntity findDate(
            @Param("checkInDate") String checkInDate,
            @Param("checkOutDate") String checkOutDate,
            @Param("roomEntity") RoomEntity roomEntity,
            @Param("checkInDatePattern") String checkInDatePattern,
            @Param("checkOutDatePattern") String checkOutDatePattern
    );


    Optional<ReserveEntity> findByIdAndMemberEntity(Long id, MemberEntity memberEntity);
}
