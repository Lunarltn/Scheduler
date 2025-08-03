package org.example.scheduler.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduler.dto.SchedulerUpdateRequestDto;
import org.example.scheduler.dto.SchedulerRequestDto;
import org.example.scheduler.dto.SchedulerResponseDto;
import org.example.scheduler.dto.SchedulerWithCommentResponseDto;
import org.example.scheduler.service.SchedulerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedulers")
@RequiredArgsConstructor
public class SchedulerController {
    private final SchedulerService schedulerService;

    @PostMapping
    public ResponseEntity<SchedulerResponseDto> PostSchedule(@RequestBody SchedulerRequestDto requestDto) {
        return new ResponseEntity<>(schedulerService.postSchedule(requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SchedulerResponseDto>> findAllSchedules() {
        return new ResponseEntity<>(schedulerService.findAllSchedules(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<SchedulerWithCommentResponseDto> findScheduleById(@PathVariable Long id) {
        return new ResponseEntity<>(schedulerService.findScheduleById(id), HttpStatus.OK);
    }

    @PatchMapping("/id/{id}/password/{password}")
    public ResponseEntity<SchedulerResponseDto> updateTitleAndAuthorWithCredentials(
            @PathVariable Long id,
            @PathVariable String password,
            @RequestBody SchedulerUpdateRequestDto requestDto
    ) {
        return new ResponseEntity<>(schedulerService.updateTitleAndAuthorWithCredentials(id, password, requestDto), HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}/password/{password}")
    public ResponseEntity<Void> deleteScheduleWithCredentials(@PathVariable Long id,
                                                              @PathVariable String password) {
        schedulerService.deleteScheduleWithCredentials(id, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
