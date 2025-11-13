package com.astralxmedia.service;

import com.astralxmedia.dto.CommentCreateRequest;
import com.astralxmedia.dto.CommentResponse;
import com.astralxmedia.entity.Comment;
import com.astralxmedia.entity.Post;
import com.astralxmedia.entity.User;
import com.astralxmedia.exception.PostNotFoundException;
import com.astralxmedia.repository.CommentRepository;
import com.astralxmedia.repository.PostRepository;
import com.astralxmedia.repository.UserRepository;
import com.astralxmedia.util.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public CommentResponse createComment(Long postId, CommentCreateRequest commentRequest, String username) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        Comment comment = Comment.builder()
                .content(commentRequest.getContent())
                .post(post)
                .user(user)
                .build();

        Comment savedComment = commentRepository.save(comment);
        return CommentMapper.toCommentResponse(savedComment);
    }

    @Override
    public List<CommentResponse> getCommentsByPost(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new PostNotFoundException("Post not found with id: " + postId);
        }
        List<Comment> comments = commentRepository.findByPostIdOrderByCreatedAtDesc(postId);
        return comments.stream().map(CommentMapper::toCommentResponse).toList();
    }
}
