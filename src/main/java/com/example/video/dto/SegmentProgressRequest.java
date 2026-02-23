package com.example.video.dto;

import lombok.Data;

@Data
public class SegmentProgressRequest {
    private String userId;
    private String lectureId;
    private int segmentIndex;
    private int watchSeconds;
    private int totalSegments;
}
