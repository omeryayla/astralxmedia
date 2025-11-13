package com.astralxmedia.dto;

import java.sql.Timestamp;

public record PostResponse(
    Long id,
    String title,
    String content,
    Timestamp createdAt,
    UserResponse author
) {
}
