package org.example.comment.service;

import org.example.comment.dto.CommentRequestDto;
import org.example.comment.dto.CommentResponseDto;
import org.springframework.stereotype.Service;

public interface CommentService {
    CommentResponseDto postComment(Long schedulerId, CommentRequestDto requestDto);
}
