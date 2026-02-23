package com.example.video.mapper;

import com.example.video.vo.LectureChapter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LectureChapterMapper {
    List<LectureChapter> selectChaptersByLecture(
            @Param("lectureId") String lectureId
    );

    int existsChapterProgress(
            @Param("userId") String userId,
            @Param("chapterId") Long chapterId
    );

    void insertChapterProgress(
            @Param("userId") String userId,
            @Param("lectureId") String lectureId,
            @Param("chapterId") Long chapterId
    );

    int countLectureChapters(
            @Param("lectureId") String lectureId
    );

    int countCompletedChapters(
            @Param("userId") String userId,
            @Param("lectureId") String lectureId
    );
}
