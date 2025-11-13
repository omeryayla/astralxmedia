package com.astralxmedia.util;

import com.astralxmedia.dto.CommentResponse;
import com.astralxmedia.dto.UserResponse;
import com.astralxmedia.entity.Comment;

public final class CommentMapper {

    private CommentMapper() {
        // Private constructor to prevent instantiation
    }

    public static CommentResponse toCommentResponse(Comment comment) {
        UserResponse author = new UserResponse(comment.getUser().getId(), comment.getUser().getUsername());
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                author
        );
    }
}
