package com.example.video.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureChapter {

    private Long chapterId;
    private int chapterIndex;
    private int startTime;
    private int endTime;
}
