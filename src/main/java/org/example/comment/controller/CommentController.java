package org.example.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.comment.dto.CommentRequestDto;
import org.example.comment.dto.CommentResponseDto;
import org.example.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedulers/id/{schedulerId}/comments")
@RequiredArgsConstructor

public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public String test() {
        return "test";
    }

    @PostMapping()
    public ResponseEntity<CommentResponseDto> postComment(@PathVariable Long schedulerId,
                                                          @RequestBody CommentRequestDto requestDto) {
        return new ResponseEntity<>(commentService.postComment(schedulerId, requestDto), HttpStatus.CREATED);
    }
}
