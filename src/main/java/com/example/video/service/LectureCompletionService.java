package com.example.video.service;

import com.example.video.dto.CompletionRequest;
import com.example.video.mapper.LectureProgressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LectureCompletionService {

    private static final double COMPLETE_RATIO = 0.8;

    private final LectureProgressMapper lectureProgressMapper;
    private final LectureChapterService lectureChapterService;

    // 수료 확인은 구간 진행 정보를 갱신하므로 쓰기 트랜잭션이 필요하다.
    @Transactional
    public boolean checkCompletion(CompletionRequest request) {
        boolean segmentCompleted = checkSegmentCompletion(request);
        boolean chapterCompleted = isLectureCompleted(request);
        return segmentCompleted && chapterCompleted;
    }

    @Transactional
    public boolean checkSegmentCompletion(CompletionRequest request) {
        Long progressId = lectureProgressMapper.findProgressId(
                request.getUserId(), request.getLectureId());

        if (progressId == null) {
            return false;
        }

        int completedSegments = lectureProgressMapper.countCompletedSegments(progressId);
        lectureProgressMapper.updateLectureProgress(progressId, completedSegments, COMPLETE_RATIO);
        return lectureProgressMapper.isLectureCompleted(request.getUserId(), request.getLectureId());
    }

    public boolean isLectureCompleted(CompletionRequest request) {
        return lectureChapterService.isLectureCompleted(request.getUserId(), request.getLectureId());
    }
}
