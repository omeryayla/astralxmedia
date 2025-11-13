package com.astralxmedia.dto;

import java.sql.Timestamp;

public record CommentResponse(
    Long id,
    String content,
    Timestamp createdAt,
    UserResponse author
) {
}
