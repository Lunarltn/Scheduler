package org.example.scheduler.dto;

import lombok.Getter;
import org.example.comment.dto.CommentRequestDto;
import org.example.comment.dto.CommentResponseDto;
import org.example.comment.entity.CommentEntity;
import org.example.scheduler.entity.SchedulerEntity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SchedulerWithCommentResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String author;
    private Date creationDate;
    private Date modificationDate;
    private List<CommentResponseDto> comments;

    public SchedulerWithCommentResponseDto(SchedulerEntity schedulerEntity) {
        this.id = schedulerEntity.getId();
        this.title = schedulerEntity.getTitle();
        this.contents = schedulerEntity.getContents();
        this.author = schedulerEntity.getAuthor();
        this.creationDate = schedulerEntity.getCreationDate();
        this.modificationDate = schedulerEntity.getModificationDate();
        this.comments = schedulerEntity.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }
}
