package com.example.video.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LectureChapterResponse {

    private Long chapterId;
    private int chapterIndex;
    private int startSec;
    private int endSec;
}
