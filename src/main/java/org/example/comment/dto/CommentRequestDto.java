package org.example.comment.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String contents;
    private String author;
    private String password;
}
