package org.example.comment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.scheduler.entity.BaseEntity;
import org.example.scheduler.entity.SchedulerEntity;

@Entity
@Getter
@NoArgsConstructor
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contents;
    private String author;
    private String password;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scheduler_id")
    private SchedulerEntity scheduler;

    public CommentEntity(String contents, String author, String password, SchedulerEntity scheduler) {
        this.contents = contents;
        this.author = author;
        this.password = password;
        this.scheduler = scheduler;
    }

}
