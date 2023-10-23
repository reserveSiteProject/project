package com.example.reservation.service;

import com.example.reservation.dto.NoticeDTO;
import com.example.reservation.dto.RoomDTO;
import com.example.reservation.entity.NoticeEntity;
import com.example.reservation.entity.RoomEntity;
import com.example.reservation.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public Long save(NoticeDTO noticeDTO) throws IOException {
        NoticeEntity noticeEntity = NoticeEntity.toSaveEntity(noticeDTO);
        return noticeRepository.save(noticeEntity).getId();
    }
    public List<NoticeDTO> notice() throws IOException {
        List<NoticeDTO> noticeDTOList = new ArrayList<>();
        List<NoticeEntity> noticeEntityList = noticeRepository.findAll();
        for(NoticeEntity entity : noticeEntityList) {
            noticeDTOList.add(NoticeDTO.toDTO(entity));
        }
        return noticeDTOList;
    }

}
