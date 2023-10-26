package com.example.reservation.service;

import com.example.reservation.dto.ReserveDTO;
import com.example.reservation.dto.RoomDTO;
import com.example.reservation.entity.MemberEntity;
import com.example.reservation.entity.ReserveEntity;
import com.example.reservation.entity.RoomEntity;
import com.example.reservation.entity.RoomFileEntity;
import com.example.reservation.repository.MemberRepository;
import com.example.reservation.repository.ReserveRepository;
import com.example.reservation.repository.RoomFileRepository;
import com.example.reservation.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReserveService {
    private final ReserveRepository reserveRepository;
    private final RoomRepository roomRepository;
    private final RoomFileRepository roomFileRepository;
    private final MemberRepository memberRepository;
    public List<ReserveDTO> findAll(){
        // reserveDTOList 생성
        List<ReserveDTO> reserveDTOList = new ArrayList<>();

        // reserve의 모든 내용을 for문으로 받아옴
        for (ReserveEntity reserve : reserveRepository.findAll()){
            // reserveDTO 데이터를 담아주는 변수 reserveDTO 생성
            ReserveDTO reserveDTO = ReserveDTO.toDTO(reserve);

            // reserveDTO의 컬럼 memberEntity와 finById를 이용해 memberEntity를 가져온다.
            MemberEntity memberEntity = memberRepository.findById(reserve.getMemberEntity().getId()).orElseThrow(() -> new RuntimeException());
            // memberEntity의 memberName을 reserveDTO의 memberName에 담아준다.
            reserveDTO.setMemberName(memberEntity.getMemberName());

            // 마찬가지로 roomEntity도 가져온 뒤, roomDTO로 변환
            RoomEntity roomEntity = roomRepository.findById(reserve.getRoomEntity().getId()).orElseThrow(() -> new RuntimeException());
            RoomDTO roomDTO = RoomDTO.toDTO(roomEntity);

            // 파일을 제외한 roomDTO의 정보는 이미 가져왔고, 파일 정보가 필요하기 때문에
            // 참조컬럼인 roomEntity를 통해 room과 참조 된 파일을 가져오기 온다.
            for (RoomFileEntity roomFile : roomFileRepository.findByRoomEntity(roomEntity)){
                List<String> originalFileName = new ArrayList<>();
                List<String> storedFileName = new ArrayList<>();


                // 가져온 값의 originalFileName과 storedFileName을 List에 저장해주고 그것을 반복
                originalFileName.add(roomFile.getOriginalFileName());
                storedFileName.add(roomFile.getStoredFileName());

                // roomDTO의 fileName컬럼에 저장
                roomDTO.setOriginalFileName(originalFileName);
                roomDTO.setStoredFileName(storedFileName);

                // reserveDTO의 roomDTO 컬럼에 여태까지 설정해놓은 roomDTO를 저장
                reserveDTO.setRoomDTO(roomDTO);

                // memberName과 roomDTO의 정보를 담은 reserveDTO를 List에 저장
                reserveDTOList.add(reserveDTO);
            }



        }

        return reserveDTOList;
    }

    public Long save(ReserveDTO reserveDTO) {
        MemberEntity memberEntity = memberRepository.findById(reserveDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        RoomEntity roomEntity = roomRepository.findById(reserveDTO.getRoomId()).orElseThrow(() -> new NoSuchElementException());
        ReserveEntity reserveEntity = ReserveEntity.toSaveEntity(reserveDTO, memberEntity, roomEntity);
        return reserveRepository.save(reserveEntity).getId();
    }
}
