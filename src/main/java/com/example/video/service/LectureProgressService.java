package com.example.video.service;

import com.example.video.dto.CompletionRequest;
import com.example.video.dto.SegmentProgressRequest;
import com.example.video.mapper.LectureProgressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@Transactional
public class LectureProgressService {

    private final LectureProgressMapper mapper;

    //private static final int SEGMENT_COMPLETE_SECONDS = 8;
    private static final double COMPLETE_RATIO = 0.8;

    /**
     * 📌 구간 시청 정보 저장 메인 로직
     */
    @Transactional
    public void saveSegmentProgress(SegmentProgressRequest req){

        // 1️⃣ progress 생성 (한 번만)
        mapper.insertLectureProgress(
                req.getUserId(),
                req.getLectureId(),
                req.getTotalSegments()
        );

        Long progressId =
                mapper.findProgressId(
                        req.getUserId(),
                        req.getLectureId()
                );

        // 2️⃣ 구간 방문 완료 처리
        mapper.insertSegmentCompleted(
                progressId,
                req.getSegmentIndex()
        );
    }

    /**
     * 🎓 수료 여부 체크
     */
    /*
    @Transactional(readOnly = true)
    public boolean checkCompletion(
            CompletionRequest req
    ) {
        Long progressId =
                mapper.findProgressId(
                        req.getUserId(),
                        req.getLectureId()
                );

        int completedSegments =
                mapper.countCompletedSegments(progressId);

        mapper.updateLectureProgress(
                progressId,
                completedSegments,
                COMPLETE_RATIO
        );

        return mapper.isLectureCompleted(
                req.getUserId(),
                req.getLectureId()
        );
    }
    */

}
