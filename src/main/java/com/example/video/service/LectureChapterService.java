package com.example.video.service;

import com.example.video.dto.ChapterProgressRequest;
import com.example.video.dto.LectureChapterResponse;
import com.example.video.mapper.LectureChapterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureChapterService {

    private final LectureChapterMapper lectureChapterMapper;

    @Transactional(readOnly = true)
    public List<LectureChapterResponse> getChaptersByLecture(String lectureId) {

        return lectureChapterMapper
                .selectChaptersByLecture(lectureId)
                .stream()
                .map(ch -> new LectureChapterResponse(
                        ch.getChapterId(),
                        ch.getChapterIndex(),
                        ch.getStartTime(),
                        ch.getEndTime()
                ))
                .toList();
    }


    /**
     * 챕터 진도 저장
     */
    @Transactional
    public void saveChapterProgress(ChapterProgressRequest req) {

        // 1️⃣ 이미 완료된 챕터인지 확인
        int exists =
                lectureChapterMapper.existsChapterProgress(
                        req.getUserId(),
                        req.getChapterId()
                );

        if (exists > 0) {
            // 이미 완료 → 중복 저장 방지
            return;
        }

        // 2️⃣ 챕터 진도 저장
        lectureChapterMapper.insertChapterProgress(
                req.getUserId(),
                req.getLectureId(),
                req.getChapterId()
        );
    }

    /**
     * 강의 수료 여부 판단
     */
    @Transactional(readOnly = true)
    public boolean isLectureCompleted(
            String userId,
            String lectureId
    ) {

        // 전체 챕터 수
        int totalChapterCount =
                lectureChapterMapper.countLectureChapters(lectureId);

        // 사용자가 완료한 챕터 수
        int completedChapterCount =
                lectureChapterMapper.countCompletedChapters(
                        userId,
                        lectureId
                );

        return totalChapterCount == completedChapterCount;
    }
}
