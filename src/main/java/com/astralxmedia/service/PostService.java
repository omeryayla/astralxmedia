package com.astralxmedia.service;

import com.astralxmedia.dto.PostCreateRequest;
import com.astralxmedia.entity.Post;
import com.astralxmedia.entity.User;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post createPost(PostCreateRequest postRequest, User author);
    List<Post> getAllPosts();
    Optional<Post> getPostById(Long id);
}
