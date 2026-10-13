package com.example.video.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LectureProgressMapper {

    Long findProgressId(@Param("userId") String userId, @Param("lectureId") String lectureId);

    void insertLectureProgress(@Param("userId") String userId,
                               @Param("lectureId") String lectureId,
                               @Param("totalSegments") int totalSegments);

    int countCompletedSegments(@Param("progressId") Long progressId);

    void updateLectureProgress(@Param("progressId") Long progressId,
                               @Param("completedSegments") int completedSegments,
                               @Param("completeRatio") double completeRatio);

    boolean isLectureCompleted(@Param("userId") String userId, @Param("lectureId") String lectureId);

    void insertSegmentCompleted(@Param("progressId") Long progressId,
                                @Param("segmentIndex") int segmentIndex);

    Integer selectLastWatchedTime(@Param("userId") String userId, @Param("lectureId") String lectureId);

    void updateLastWatchedTime(@Param("userId") String userId,
                              @Param("lectureId") String lectureId,
                              @Param("lastWatchedTime") int lastWatchedTime);
}
