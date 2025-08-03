package org.example.comment.repository;

import org.example.comment.dto.CommentRequestDto;
import org.example.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
