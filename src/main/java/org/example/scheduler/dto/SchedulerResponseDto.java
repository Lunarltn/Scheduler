package org.example.scheduler.dto;

import lombok.Getter;
import org.example.scheduler.entity.SchedulerEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter

public class SchedulerResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String writer;
    private String password;
    private Date creationDate;
    private Date modificationDate;

    public SchedulerResponseDto(SchedulerEntity schedulerEntity) {
        this.id = schedulerEntity.getId();
        this.title = schedulerEntity.getTitle();
        this.contents = schedulerEntity.getContents();
        this.writer = schedulerEntity.getWriter();
        this.password = schedulerEntity.getPassword();
        this.creationDate = schedulerEntity.getCreationDate();
        this.modificationDate = schedulerEntity.getModificationDate();
    }
}
