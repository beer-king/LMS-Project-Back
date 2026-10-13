package com.example.video.service;

import com.example.video.dto.CompletionRequest;
import com.example.video.mapper.LectureProgressMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LectureCompletionServiceTests {

    private final LectureProgressMapper progressMapper = mock(LectureProgressMapper.class);
    private final LectureChapterService chapterService = mock(LectureChapterService.class);
    private final LectureCompletionService service = new LectureCompletionService(progressMapper, chapterService);

    private CompletionRequest request() {
        CompletionRequest request = new CompletionRequest();
        request.setUserId("user");
        request.setLectureId("lecture");
        return request;
    }

    @ParameterizedTest
    @CsvSource({"true,true,true", "true,false,false", "false,true,false", "false,false,false"})
    void completionRequiresBothSegmentsAndChapters(boolean segments, boolean chapters, boolean expected) {
        when(progressMapper.findProgressId("user", "lecture")).thenReturn(1L);
        when(progressMapper.countCompletedSegments(1L)).thenReturn(4);
        when(progressMapper.isLectureCompleted("user", "lecture")).thenReturn(segments);
        when(chapterService.isLectureCompleted("user", "lecture")).thenReturn(chapters);

        assertEquals(expected, service.checkCompletion(request()));
        verify(progressMapper).updateLectureProgress(1L, 4, 0.8);
    }

    @Test
    void missingProgressIsIncompleteEvenWhenChaptersAreComplete() {
        when(progressMapper.findProgressId("user", "lecture")).thenReturn(null);
        when(chapterService.isLectureCompleted("user", "lecture")).thenReturn(true);

        assertFalse(service.checkCompletion(request()));
        verify(progressMapper).findProgressId("user", "lecture");
        verifyNoMoreInteractions(progressMapper);
    }
}
