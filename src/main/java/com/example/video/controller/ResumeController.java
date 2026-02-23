package com.example.video.controller;

import com.example.video.dto.ResumeRequest;
import com.example.video.dto.ResumeResponse;
import com.example.video.service.LectureResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/progress")
public class ResumeController {

    private final LectureResumeService lectureResumeService;
    /*
    마지막 시청 위치 조회
    * */
    @GetMapping("/resume/{lectureId}")
    public ResponseEntity<ResumeResponse> getResume(@PathVariable String lectureId, @RequestParam String userId){
        int lastTime =
                lectureResumeService.getLastWatchedTime(userId, lectureId);

        return ResponseEntity.ok(
                new ResumeResponse(lastTime)
        );
    }

    /**
     * 🔥 마지막 시청 위치 저장
     */
    @PostMapping("/resume")
    public ResponseEntity<Void> saveResume(@RequestBody ResumeRequest request) {
        lectureResumeService.saveLastWatchedTime(request);
        return ResponseEntity.ok().build();
    }

}
