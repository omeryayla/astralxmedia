package com.astralxmedia.service;

import com.astralxmedia.dto.PostCreateRequest;
import com.astralxmedia.entity.Post;
import com.astralxmedia.entity.User;
import com.astralxmedia.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Post createPost(PostCreateRequest postRequest, User author) {
        Post post = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .user(author)
                .build();
        return postRepository.save(post);
    }
}
