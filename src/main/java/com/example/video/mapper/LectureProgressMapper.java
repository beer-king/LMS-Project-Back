package com.example.video.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LectureProgressMapper {

    Long findProgressId(
            String userId,
            String lectureId
    );

    void insertLectureProgress(
            String userId,
            String lectureId,
            int totalSegments
    );

    void insertLectureProgress(
            String userId,
            String lectureId
    );

    int countTotalSegments(Long lectureId);

    void upsertSegmentProgress(
            Long progressId,
            int segmentIndex,
            int watchSeconds,
            int completeSeconds
    );

    // 완료된 구간 수
    int countCompletedSegments(
            Long progressId
    );

    void updateLectureProgress(
            Long progressId,
            int completedSegments,
            double completeRatio
    );

    boolean isLectureCompleted(
            String userId,
            String lectureId
    );

    void insertSegmentCompleted(
            Long progressId,
            int segmentIndex
    );

    int selectLastWatchedTime(
            String userId,
            String lectureId
    );

    void updateLastWatchedTime(String userId, String lectureId, int lastWatchedTime);
}
