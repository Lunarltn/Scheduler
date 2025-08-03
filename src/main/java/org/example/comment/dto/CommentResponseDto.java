package org.example.comment.dto;

import lombok.Getter;
import org.example.comment.entity.CommentEntity;

import javax.xml.stream.events.Comment;
import java.util.Date;

@Getter
public class CommentResponseDto {
    private Long id;
    private String contents;
    private String author;
    private Date creationDate;
    private Date modificationDate;
    private Long scheduleId;

    public CommentResponseDto(CommentEntity commentEntity) {
        this.id = commentEntity.getId();
        this.contents = commentEntity.getContents();
        this.author = commentEntity.getAuthor();
        this.creationDate = commentEntity.getCreationDate();
        this.modificationDate = commentEntity.getModificationDate();
        this.scheduleId = commentEntity.getScheduler().getId();
    }
}
