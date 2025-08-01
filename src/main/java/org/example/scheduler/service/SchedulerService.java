package org.example.scheduler.service;

import org.example.scheduler.dto.SchedulerRequestDto;
import org.example.scheduler.dto.SchedulerResponseDto;

public interface SchedulerService {
    SchedulerResponseDto postSchedule(SchedulerRequestDto requestDto);
}
