package com.example.video.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChapterProgressRequest {
    private String userId;
    private String lectureId;
    private Long chapterId;
}

