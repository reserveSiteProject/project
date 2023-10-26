package com.example.reservation.service;

import com.example.reservation.dto.RoomDTO;
import com.example.reservation.entity.RoomEntity;
import com.example.reservation.entity.RoomFileEntity;
import com.example.reservation.repository.RoomFileRepository;
import com.example.reservation.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomFileRepository roomFileRepository;



    @Transactional
    public void save(RoomDTO roomDTO) throws IOException {
        if (roomDTO.getRoomFileName().get(0).isEmpty()){
            RoomEntity roomEntity = RoomEntity.toSaveEntity(roomDTO);
            roomRepository.save(roomEntity);
        } else {
            RoomEntity roomEntity = RoomEntity.toSaveEntityWithFile(roomDTO);
            RoomEntity savedEntity = roomRepository.save(roomEntity);

            for (MultipartFile roomFile : roomDTO.getRoomFileName()){
                String originalFileName = roomFile.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "-" + originalFileName;
                String savePath = "C:\\final_img\\" + storedFileName;

                roomFile.transferTo(new File(savePath));
                RoomFileEntity roomFileEntity = RoomFileEntity.toSaveEntity(originalFileName, storedFileName, savedEntity);

                System.out.println(roomFileEntity + "+" + savedEntity);
                roomFileRepository.save(roomFileEntity);
            }


        }
    }



    @Transactional
    public List<RoomDTO> findAll() {
        List<RoomEntity> roomEntityList = roomRepository.findAll();
        List<RoomDTO> roomDTOList = new ArrayList<>();
        roomEntityList.forEach(room -> {
            roomDTOList.add(RoomDTO.toDTO(room));
        });
        return roomDTOList;
    }

    @Transactional
    public RoomDTO findById(Long roomId) {
        RoomEntity roomEntity = roomRepository.findById(roomId).orElseThrow(() -> new NoSuchElementException());
        return RoomDTO.toDTO(roomEntity);
    }
}
