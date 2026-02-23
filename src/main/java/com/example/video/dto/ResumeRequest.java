package com.example.video.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumeRequest {

    private String userId;          // 사용자 ID
    private String lectureId;       // 강의 ID
    private int lastWatchedTime;    // 마지막 시청 초
}
