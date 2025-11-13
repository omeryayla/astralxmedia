package com.astralxmedia.service;

import com.astralxmedia.dto.PostCreateRequest;
import com.astralxmedia.dto.PostResponse;
import com.astralxmedia.entity.User;

import java.util.List;
import java.util.Optional;

public interface PostService {
    PostResponse createPost(PostCreateRequest postRequest, User author);
    List<PostResponse> getAllPosts();
    Optional<PostResponse> getPostById(Long id);
}
