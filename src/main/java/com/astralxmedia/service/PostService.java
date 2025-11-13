package com.astralxmedia.service;

import com.astralxmedia.dto.PostCreateRequest;
import com.astralxmedia.entity.Post;
import com.astralxmedia.entity.User;

public interface PostService {
    Post createPost(PostCreateRequest postRequest, User author);
}
