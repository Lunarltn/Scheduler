package org.example.scheduler.service;

import org.example.comment.entity.CommentEntity;
import org.example.scheduler.dto.SchedulerUpdateRequestDto;
import org.example.scheduler.dto.SchedulerRequestDto;
import org.example.scheduler.dto.SchedulerResponseDto;
import org.example.scheduler.dto.SchedulerWithCommentResponseDto;
import org.example.scheduler.entity.SchedulerEntity;

import java.util.List;

public interface SchedulerService {
    SchedulerResponseDto postSchedule(SchedulerRequestDto requestDto);

    List<SchedulerResponseDto> findAllSchedules();

    SchedulerWithCommentResponseDto findScheduleById(Long id);

    SchedulerResponseDto updateTitleAndAuthorWithCredentials(Long id, String password, SchedulerUpdateRequestDto requestDto);

    void deleteScheduleWithCredentials(Long id, String password);

    SchedulerEntity getSchedulerEntityByIdOrThrow(Long id);

    void addComment(Long id, CommentEntity commentEntity);
}
