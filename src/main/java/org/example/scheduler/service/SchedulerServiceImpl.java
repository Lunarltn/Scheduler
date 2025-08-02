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

    @Override
    public List<SchedulerResponseDto> findAllSchedules() {
        List<SchedulerEntity> list = schedulerRepository.findAll();
        List<SchedulerResponseDto> schedulerResponseDtoList = new ArrayList<>(list.stream().map(SchedulerResponseDto::new).toList());
        schedulerResponseDtoList.sort(new SchedulerResponseDtoDateComparator().reversed());
        return schedulerResponseDtoList;
    }

    @Override
    public SchedulerResponseDto findScheduleById(Long id) {
        Optional<SchedulerEntity> byId = schedulerRepository.findById(id);
        if (byId.isPresent())
            return new SchedulerResponseDto(byId.get());
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found");
    }

    @Override
    public SchedulerResponseDto updateTitleAndAuthorWithCredentials(Long id, String password, PatchSchedulerRequestDto requestDto) {
        Optional<SchedulerEntity> byId = schedulerRepository.findById(id);

        if (byId.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found");

        if (!byId.get().getPassword().equals(password))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password does not match");

        if (requestDto.getTitle().isEmpty() || requestDto.getAuthor().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title or author is empty");

        SchedulerEntity schedulerEntity = new SchedulerEntity(requestDto.getTitle(), byId.get().getContents(), requestDto.getAuthor(), byId.get().getPassword());
        schedulerRepository.save(schedulerEntity);

        return new SchedulerResponseDto(schedulerEntity);
    }
}
