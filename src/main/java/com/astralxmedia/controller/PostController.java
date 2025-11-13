package com.astralxmedia.controller;

import com.astralxmedia.dto.PostCreateRequest;
import com.astralxmedia.entity.Post;
import com.astralxmedia.entity.User;
import com.astralxmedia.repository.UserRepository;
import com.astralxmedia.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity<Post> createPost(@RequestBody PostCreateRequest postRequest, Principal principal) {
        String username = principal.getName();
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        Post newPost = postService.createPost(postRequest, author);

        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }
}
