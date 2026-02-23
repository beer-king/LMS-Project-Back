package com.example.video.service;

import com.example.video.dto.CompletionRequest;
import com.example.video.mapper.LectureChapterMapper;
import com.example.video.mapper.LectureProgressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LectureCompletionService {

    private final LectureProgressMapper lectureProgressMapper;
    private final LectureChapterMapper lectureChapterMapper;

    private static final double COMPLETE_RATIO = 0.8;

    // 수료판단 종합(구간&챕터)
    public boolean checkCompletion(CompletionRequest req){
        boolean checkSegmentComp = checkSegmentCompletion(req);
        boolean checkChapterComp =  isLectureCompleted(req);

        return checkChapterComp && checkSegmentComp;
    }

    /*
     * 🎓 수료 여부 체크(구간)
     */
    @Transactional(readOnly = true)
    public boolean checkSegmentCompletion(CompletionRequest req) {
        Long progressId =
                lectureProgressMapper.findProgressId(
                        req.getUserId(),
                        req.getLectureId()
                );

        int completedSegments =
                lectureProgressMapper.countCompletedSegments(progressId);

        lectureProgressMapper.updateLectureProgress(
                progressId,
                completedSegments,
                COMPLETE_RATIO
        );

        return lectureProgressMapper.isLectureCompleted(
                req.getUserId(),
                req.getLectureId()
        );
    }


    /**
     * 강의 수료 여부 판단(챕터기준)
     */
    public boolean isLectureCompleted(CompletionRequest req) {

        // 전체 챕터 수
        int totalChapterCount =
                lectureChapterMapper.countLectureChapters(req.getLectureId());

        // 사용자가 완료한 챕터 수
        int completedChapterCount =
                lectureChapterMapper.countCompletedChapters(
                        req.getUserId(),
                        req.getLectureId()
                );

        return totalChapterCount == completedChapterCount;
    }
}
