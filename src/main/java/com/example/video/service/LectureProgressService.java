package com.example.video.service;

import com.example.video.dto.SegmentProgressRequest;
import com.example.video.mapper.LectureProgressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LectureProgressService {

    private final LectureProgressMapper lectureProgressMapper;

    // 부모 진행 정보와 완료 구간을 하나의 트랜잭션으로 저장한다.
    @Transactional
    public void saveSegmentProgress(SegmentProgressRequest request) {
        lectureProgressMapper.insertLectureProgress(
                request.getUserId(), request.getLectureId(), request.getTotalSegments());

        Long progressId = lectureProgressMapper.findProgressId(
                request.getUserId(), request.getLectureId());
        lectureProgressMapper.insertSegmentCompleted(progressId, request.getSegmentIndex());
    }
}
