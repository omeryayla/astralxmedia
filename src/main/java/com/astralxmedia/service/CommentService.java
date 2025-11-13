package com.astralxmedia.service;

import com.astralxmedia.dto.CommentCreateRequest;
import com.astralxmedia.dto.CommentResponse;

import java.util.List;

public interface CommentService {
    CommentResponse createComment(Long postId, CommentCreateRequest commentRequest, String username);
    List<CommentResponse> getCommentsByPost(Long postId);
}
