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
    
    /*
    마지막 위치 조회
    * */
    @Transactional(readOnly = true)
    public int getLastWatchedTime(String userId, String lectureId){

        Integer time =
                lectureProgressMapper.selectLastWatchedTime(
                        userId,
                        lectureId
                );

        return time == null ? 0 : time;
    }

    /**
     * 🔥 마지막 시청 위치 저장
     *
     * 설계 이유:
     * 1. progress row는 이미 존재한다고 가정
     * 2. 없다면 생성 로직 추가 가능
     */
    @Transactional
    public void saveLastWatchedTime(ResumeRequest req) {

        Long progressId = lectureProgressMapper.findProgressId(req.getUserId(), req.getLectureId());

        if(progressId == null){ // progressId 없는경우 생성
            lectureProgressMapper.insertLectureProgress(req.getUserId(), req.getLectureId());

            progressId = lectureProgressMapper.findProgressId(req.getUserId(), req.getLectureId());
        }

        // 마지막 시청위치 저장!!
        lectureProgressMapper.updateLastWatchedTime(
                progressId,
                req.getLastWatchedTime()
        );

    }
}
