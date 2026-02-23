package com.example.video.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LectureProgress {
    private Long progressId;
    private String userId;
    private Long lectureId;
    private Integer totalSegments;
    private Integer completedSegments;
    private String completedYn;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
