package com.example.video.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureCompleteRequest {
    private String userId;
    private String lectureId;
}
