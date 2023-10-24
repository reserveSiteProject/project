//package com.example.reservation.dto;
//
//import com.example.reservation.entity.MemberEntity;
//import com.example.reservation.entity.PaymentEntity;
//import com.example.reservation.entity.ReserveEntity;
//import com.example.reservation.entity.RoomEntity;
//import lombok.Data;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
//@Data
//public class ReserveListDTO  {
//    private int persons;
//    private List<MultipartFile> RoomFileName;
//    private String roomName;
//    private Long totalPrice;
//    private String checkInDate;
//    private String checkOutDate;
//
//    public static ReserveListDTO toDTO(PaymentEntity paymentEntity, RoomEntity roomEntity, ReserveEntity reserveEntity, MemberEntity memberEntity){
//        ReserveListDTO reserveListDTO = new ReserveListDTO();
//        reserveListDTO.setPersons(reserveEntity.getPersons());
//        if(roomEntity.getRoomFileEntityList().get(0).getOriginalFileName().isEmpty()){
//            reserveListDTO.setRoomFileName();
//        }else{
//
//        }
//
//    }
//}
