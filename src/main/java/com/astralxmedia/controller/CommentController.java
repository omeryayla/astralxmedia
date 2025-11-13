package com.astralxmedia.controller;

import com.astralxmedia.dto.CommentCreateRequest;
import com.astralxmedia.dto.CommentResponse;
import com.astralxmedia.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long postId,
                                                         @RequestBody CommentCreateRequest commentRequest,
                                                         Principal principal) {
        CommentResponse newComment = commentService.createComment(postId, commentRequest, principal.getName());
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<CommentResponse>> getCommentsByPost(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }
}
