package com.example.video.service;

import com.example.video.dto.ResumeRequest;
import com.example.video.mapper.LectureProgressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LectureResumeService {

    private final LectureProgressMapper lectureProgressMapper;

    @Transactional(readOnly = true)
    public int getLastWatchedTime(String userId, String lectureId) {
        Integer lastWatchedTime = lectureProgressMapper.selectLastWatchedTime(userId, lectureId);
        return lastWatchedTime == null ? 0 : lastWatchedTime;
    }

    // 진행 정보가 존재하는 경우에만 저장하며, SQL에서 이전보다 큰 위치만 반영한다.
    @Transactional
    public void saveLastWatchedTime(ResumeRequest request) {
        lectureProgressMapper.updateLastWatchedTime(
                request.getUserId(), request.getLectureId(), request.getLastWatchedTime());
    }
}
