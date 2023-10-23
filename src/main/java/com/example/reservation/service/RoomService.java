package com.example.reservation.service;

import com.example.reservation.dto.RoomDTO;
import com.example.reservation.entity.RoomEntity;
import com.example.reservation.entity.RoomFileEntity;
import com.example.reservation.repository.RoomFileRepository;
import com.example.reservation.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomFileRepository roomFileRepository;



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
                String savePath = "C:\\springBootFinalProject_img\\" + storedFileName;

                roomFile.transferTo(new File(savePath));
                RoomFileEntity roomFileEntity = RoomFileEntity.toSaveEntity(originalFileName, storedFileName, savedEntity);
                roomFileRepository.save(roomFileEntity);
            }


        }
    }
}
