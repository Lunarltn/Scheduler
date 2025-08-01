package org.example.scheduler.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduler.dto.SchedulerRequestDto;
import org.example.scheduler.dto.SchedulerResponseDto;
import org.example.scheduler.entity.SchedulerEntity;
import org.example.scheduler.repository.SchedulerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {
    private final SchedulerRepository schedulerRepository;


    @Transactional
    @Override
    public SchedulerResponseDto postSchedule(SchedulerRequestDto requestDto) {
        SchedulerEntity schedulerEntity = new SchedulerEntity(
                requestDto.getTitle(),
                requestDto.getContents(),
                requestDto.getWriter(),
                requestDto.getPassword()
        );
        schedulerRepository.save(schedulerEntity);

        return new SchedulerResponseDto(schedulerEntity);
    }
}
