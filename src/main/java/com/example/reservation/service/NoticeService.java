package com.example.reservation.service;

import com.example.reservation.dto.NoticeDTO;
import com.example.reservation.entity.NoticeEntity;
import com.example.reservation.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public NoticeDTO findById(Long id) throws IOException {
        NoticeEntity noticeEntity = noticeRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        return NoticeDTO.toDTO(noticeEntity);
    }
}
