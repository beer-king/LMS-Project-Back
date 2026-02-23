package com.example.video.dto;

import lombok.Data;

@Data
public class SegmentCompleteReq {
    private String userId;
    private String lectureId;
    private Integer segmentIndex; // 완료한 구간 번호 (0,1,2...)
}
