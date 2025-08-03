package org.example.scheduler.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduler.dto.PatchSchedulerRequestDto;
import org.example.scheduler.dto.SchedulerRequestDto;
import org.example.scheduler.dto.SchedulerResponseDto;
import org.example.scheduler.dto.SchedulerResponseDtoDateComparator;
import org.example.scheduler.entity.SchedulerEntity;
import org.example.scheduler.repository.SchedulerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

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
                requestDto.getAuthor(),
                requestDto.getPassword()
        );
        schedulerRepository.save(schedulerEntity);

        return new SchedulerResponseDto(schedulerEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<SchedulerResponseDto> findAllSchedules() {
        List<SchedulerEntity> list = schedulerRepository.findAll();
        List<SchedulerResponseDto> schedulerResponseDtoList = new ArrayList<>(list.stream().map(SchedulerResponseDto::new).toList());
        schedulerResponseDtoList.sort(new SchedulerResponseDtoDateComparator().reversed());
        return schedulerResponseDtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public SchedulerResponseDto findScheduleById(Long id) {
        SchedulerEntity byId = schedulerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found"));

        return new SchedulerResponseDto(byId);
    }

    @Transactional
    @Override
    public SchedulerResponseDto updateTitleAndAuthorWithCredentials(Long id, String password, PatchSchedulerRequestDto requestDto) {
        if (requestDto.getTitle().isEmpty() || requestDto.getAuthor().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title or author is empty");

        SchedulerEntity schedulerEntity = schedulerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found"));

        if (!schedulerEntity.getPassword().equals(password))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password does not match");

        schedulerEntity.updateTitleAndAuthor(requestDto.getTitle(), requestDto.getAuthor());

        return new SchedulerResponseDto(schedulerEntity);
    }

    @Transactional
    @Override
    public void deleteScheduleWithCredentials(Long id, String password) {
        SchedulerEntity schedulerEntity = schedulerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found"));

        if (!schedulerEntity.getPassword().equals(password))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password does not match");

        schedulerRepository.deleteById(id);
    }
}
