package com.astralxmedia.service;

import com.astralxmedia.dto.PostCreateRequest;
import com.astralxmedia.dto.PostResponse;
import com.astralxmedia.entity.Post;
import com.astralxmedia.entity.User;
import com.astralxmedia.repository.PostRepository;
import com.astralxmedia.util.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public PostResponse createPost(PostCreateRequest postRequest, User author) {
        Post post = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .user(author)
                .build();
        Post savedPost = postRepository.save(post);
        return PostMapper.toPostResponse(savedPost);
    }

    @Override
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream()
                .map(PostMapper::toPostResponse)
                .toList();
    }

    @Override
    public Optional<PostResponse> getPostById(Long id) {
        return postRepository.findById(id).map(PostMapper::toPostResponse);
    }
}
