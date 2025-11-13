package com.astralxmedia.service;

import com.astralxmedia.dto.PostCreateRequest;
import com.astralxmedia.dto.PostResponse;
import com.astralxmedia.dto.PostUpdateRequest;
import com.astralxmedia.entity.Post;
import com.astralxmedia.entity.User;
import com.astralxmedia.exception.PostNotFoundException;
import com.astralxmedia.exception.UnauthorizedOperationException;
import com.astralxmedia.exception.ValidationException;
import com.astralxmedia.repository.PostRepository;
import com.astralxmedia.repository.UserRepository;
import com.astralxmedia.util.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

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

    @Override
    public void deletePost(Long postId, String username) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        if (!post.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedOperationException("User is not authorized to delete this post.");
        }

        postRepository.delete(post);
    }

    @Override
    public PostResponse updatePost(Long postId, PostUpdateRequest updateRequest, String username) {
        if ((updateRequest.getTitle() == null || updateRequest.getTitle().isEmpty()) &&
            (updateRequest.getContent() == null || updateRequest.getContent().isEmpty())) {
            throw new ValidationException("Title or content must be provided for an update.");
        }

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        if (!post.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedOperationException("User is not authorized to update this post.");
        }

        if (updateRequest.getTitle() != null) {
            post.setTitle(updateRequest.getTitle());
        }
        if (updateRequest.getContent() != null) {
            post.setContent(updateRequest.getContent());
        }

        Post updatedPost = postRepository.save(post);
        return PostMapper.toPostResponse(updatedPost);
    }
}
