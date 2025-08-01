package org.example.scheduler.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduler.dto.SchedulerRequestDto;
import org.example.scheduler.dto.SchedulerResponseDto;
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
    public ResponseEntity<SchedulerResponseDto> PostSchedule(@RequestBody SchedulerRequestDto requestDto)
    {
        return new ResponseEntity<>(schedulerService.postSchedule(requestDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SchedulerResponseDto>> findAllSchedules()
    {
        return new ResponseEntity<>(schedulerService.findAllSchedules(),HttpStatus.OK);
    }

}
