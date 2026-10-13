package com.example.video.controller;

import com.example.video.dto.CompletionRequest;
import com.example.video.dto.CompletionResponse;
import com.example.video.dto.SegmentProgressRequest;
import com.example.video.service.LectureCompletionService;
import com.example.video.service.LectureProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/progress")
public class ProgressController {

    private final LectureProgressService lectureProgressService;
    private final LectureCompletionService lectureCompletionService;

    /**
    서버는 초 단위 시청 시간을 저장하지 않는다.
    프론트에서 최소 체류 조건을 만족한 구간만
    완료 처리하며, 각 구간은 1회만 인정된다.
     */


    /**
     * 📡 구간 시청 정보 저장
     * - 프론트 timeupdate 이벤트에서 주기적으로 호출됨
     */
    @PostMapping("/segment")
    public void saveSegmentProgress(
            @RequestBody SegmentProgressRequest request
    ) {
        lectureProgressService.saveSegmentProgress(request);
    }


    /**
     * 🎓 수료 여부 체크(구간 및 챕터)
     * - 영상 종료 시 호출
     */
    @PostMapping("/complete/check")
    public ResponseEntity<CompletionResponse> checkCompletion(
            @RequestBody CompletionRequest request
    ) {
        boolean completed =
                lectureCompletionService.checkCompletion(request);

        return ResponseEntity.ok(
                new CompletionResponse(completed)
        );
    }

}
