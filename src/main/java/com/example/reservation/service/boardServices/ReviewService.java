package com.example.reservation.service.boardServices;

import com.example.reservation.dto.ReviewDTO;
import com.example.reservation.entity.ReviewEntity;
import com.example.reservation.entity.ReviewFileEntity;
import com.example.reservation.repository.boardRepositories.ReviewFileRepository;
import com.example.reservation.repository.boardRepositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Profile("!test")
public class ReviewService {

    /*다음은 테스트를 하기 이전의 접근 제한자를 적용한 필드들이다.
    타 팀원이 해당 레파지토리에 대한 pull request를 하고 merge를 한 이후에 김태훈이 주석 삭제하기.

    private final MemberRepository memberRepository;
    private final PaymentRepository paymentRepository;

     */

    private final ReviewRepository reviewRepository;
    private final ReviewFileRepository reviewFileRepository;

    public Long save(ReviewDTO reviewDTO) throws IOException {
//        MemberEntity memberEntity = memberRepository.findByMemberEmail(reviewDTO.getReviewWriter()).orElseThrow(() -> new NoSuchElementException());
        if (reviewDTO.getReviewFile().get(0).isEmpty() && reviewDTO.getReviewFile() != null) {
            // 첨부파일 없음
//            ReviewEntity reviewEntity = ReviewEntity.toSaveEntity(memberEntity, paymentEntity, reviewDTO);
              ReviewEntity reviewEntity = ReviewEntity.toSaveEntity(reviewDTO);

            return reviewRepository.save(reviewEntity).getId();
        } else {
            // 첨부파일 있음
//            ReviewEntity reviewEntity = ReviewEntity.toSaveEntityWithFile(memberEntity, paymentEntity, reviewDTO);
            ReviewEntity reviewEntity = ReviewEntity.toSaveEntityWithFile(reviewDTO);

            // 게시글 저장처리 후 저장한 엔티티 가져옴
            ReviewEntity savedEntity = reviewRepository.save(reviewEntity);
            // 파일 이름 처리, 파일 로컬에 저장 등
            // DTO에 담긴 파일리스트 꺼내기
            for (MultipartFile reviewFile: reviewDTO.getReviewFile()) {
                // 업로드한 파일 이름
                String originalFilename = reviewFile.getOriginalFilename();
                // 저장용 파일 이름
                String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
                // 저장경로+파일이름 준비
                String savePath = "C:\\springboot_reviewImg\\" + storedFileName;
                // 파일 폴더에 저장
                reviewFile.transferTo(new File(savePath));
                // 파일 정보 board_file_table에 저장
                // 파일 정보 저장을 위한 BoardFileEntity 생성
                ReviewFileEntity reviewFileEntity =
                        ReviewFileEntity.toSaveReviewFile(savedEntity, originalFilename, storedFileName);
                reviewFileRepository.save(reviewFileEntity);
            }
            return savedEntity.getId();
        }
    }
}
