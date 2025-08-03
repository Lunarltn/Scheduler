package org.example.comment.service;

import lombok.RequiredArgsConstructor;
import org.example.comment.dto.CommentRequestDto;
import org.example.comment.dto.CommentResponseDto;
import org.example.comment.entity.CommentEntity;
import org.example.comment.repository.CommentRepository;
import org.example.scheduler.entity.SchedulerEntity;
import org.example.scheduler.service.SchedulerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final SchedulerService schedulerService;

    @Transactional
    @Override
    public CommentResponseDto postComment(Long schedulerId, CommentRequestDto requestDto) {
        SchedulerEntity schedulerEntity = schedulerService.getSchedulerEntityByIdOrThrow(schedulerId);

        CommentEntity commentEntity = new CommentEntity(
                requestDto.getContents(),
                requestDto.getAuthor(),
                requestDto.getPassword(),
                schedulerEntity
        );
        schedulerEntity.addComment(commentEntity);
        commentRepository.save(commentEntity);
        return new CommentResponseDto(commentEntity);
    }
}
