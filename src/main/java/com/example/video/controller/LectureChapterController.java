package com.example.video.controller;

import com.example.video.dto.ChapterProgressRequest;
import com.example.video.dto.LectureChapterResponse;
import com.example.video.service.LectureChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lecture")
public class LectureChapterController {
    private final LectureChapterService lectureChapterService;

    /**
     * 강의 챕터 목록 조회
     */
    @GetMapping("/chapters/{lectureId}")
    public ResponseEntity<List<LectureChapterResponse>> getChapters(
            @PathVariable String lectureId
    ) {
        return ResponseEntity.ok(
                lectureChapterService.getChaptersByLecture(lectureId)
        );
    }

    /**
     * 챕터 진도 저장
     */
    @PostMapping("/chapter/progress")
    public ResponseEntity<Void> saveChapterProgress(
            @RequestBody ChapterProgressRequest req
    ) {
        lectureChapterService.saveChapterProgress(req);
        return ResponseEntity.ok().build();
    }

}
