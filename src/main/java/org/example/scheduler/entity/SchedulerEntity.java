package org.example.scheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.comment.entity.CommentEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class SchedulerEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30, nullable = false)
    private String title;
    @Column(length = 200, nullable = false)
    private String contents;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "scheduler", orphanRemoval = true)
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

    public void checkPasswordOrThrow(String password) {
        if (!this.password.equals(password))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 맞지 않습니다.");
    }
}
