package org.example.scheduler.service;

import lombok.RequiredArgsConstructor;
import org.example.comment.entity.CommentEntity;
import org.example.scheduler.dto.*;
import org.example.scheduler.entity.SchedulerEntity;
import org.example.scheduler.repository.SchedulerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

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
    public SchedulerWithCommentResponseDto findScheduleById(Long id) {
        return new SchedulerWithCommentResponseDto(getSchedulerEntityByIdOrThrow(id));
    }

    @Transactional
    @Override
    public SchedulerResponseDto updateTitleAndAuthorWithCredentials(Long id, String password, SchedulerUpdateRequestDto requestDto) {
        if (requestDto.getTitle().isEmpty() || requestDto.getAuthor().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "제목 또는 작성자를 적어주세요.");

        SchedulerEntity schedulerEntity = getSchedulerEntityByIdOrThrow(id);

        schedulerEntity.checkPasswordOrThrow(password);

        schedulerEntity.updateTitleAndAuthor(requestDto.getTitle(), requestDto.getAuthor());

        return new SchedulerResponseDto(schedulerEntity);
    }

    @Transactional
    @Override
    public void deleteScheduleWithCredentials(Long id, String password) {
        SchedulerEntity schedulerEntity = getSchedulerEntityByIdOrThrow(id);

        schedulerEntity.checkPasswordOrThrow(password);

        schedulerRepository.deleteById(id);
    }

    @Override
    public SchedulerEntity getSchedulerEntityByIdOrThrow(Long id) {
        return schedulerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다."));
    }

    @Transactional
    @Override
    public void addComment(Long id, CommentEntity commentEntity) {
        getSchedulerEntityByIdOrThrow(id).addComment(commentEntity);
    }
}
