package org.example.scheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.comment.entity.CommentEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class SchedulerEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String contents;
    private String author;
    private String password;
    @OneToMany(mappedBy = "scheduler")
    private List<CommentEntity> comments = new ArrayList<>();

    public SchedulerEntity(String title, String contents, String author, String password) {
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.password = password;
    }

    public void updateTitleAndAuthor(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public void addComment(CommentEntity commentEntity) {
        if (comments.size() >= 10)
            throw new IllegalStateException("댓글은 10개까지 등록할 수 있습니다.");
        comments.add(commentEntity);
        commentEntity.setScheduler(this);
    }
}
