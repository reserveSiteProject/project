package com.example.reservation.service.boardServices;

import com.example.reservation.dto.NoticeDTO;
import com.example.reservation.entity.NoticeEntity;
import com.example.reservation.repository.boardRepositories.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public Long save(NoticeDTO noticeDTO) throws IOException {
        NoticeEntity noticeEntity = NoticeEntity.toSaveEntity(noticeDTO);
        return noticeRepository.save(noticeEntity).getId();
    }
}
