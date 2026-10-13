package com.example.video.service;

import com.example.video.mapper.LectureProgressMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LectureResumeServiceTests {

    private final LectureProgressMapper mapper = mock(LectureProgressMapper.class);
    private final LectureResumeService service = new LectureResumeService(mapper);

    @Test
    void missingProgressStartsAtZero() {
        when(mapper.selectLastWatchedTime("user", "lecture")).thenReturn(null);
        assertEquals(0, service.getLastWatchedTime("user", "lecture"));
    }

    @Test
    void existingProgressResumesAtSavedPosition() {
        when(mapper.selectLastWatchedTime("user", "lecture")).thenReturn(42);
        assertEquals(42, service.getLastWatchedTime("user", "lecture"));
    }
}
