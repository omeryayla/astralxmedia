package com.astralxmedia.util;

import com.astralxmedia.dto.PostResponse;
import com.astralxmedia.dto.UserResponse;
import com.astralxmedia.entity.Post;

public final class PostMapper {

    private PostMapper() {
        // Private constructor to prevent instantiation
    }

    public static PostResponse toPostResponse(Post post) {
        UserResponse author = new UserResponse(post.getUser().getId(), post.getUser().getUsername());

        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                author
        );
    }
}
