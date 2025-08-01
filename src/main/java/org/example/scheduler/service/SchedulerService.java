package org.example.scheduler.service;

import org.example.scheduler.dto.SchedulerRequestDto;
import org.example.scheduler.dto.SchedulerResponseDto;

import java.util.List;

public interface SchedulerService {
    SchedulerResponseDto postSchedule(SchedulerRequestDto requestDto);

    List<SchedulerResponseDto> findAllSchedules();

    SchedulerResponseDto findScheduleById(Long id);
}
