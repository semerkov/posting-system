package com.services.post.controller;

import com.services.post.converter.PostConverter;
import com.services.post.domain.Post;
import com.services.post.dto.request.PostRequestDto;
import com.services.post.dto.request.PostUpdateRequestDto;
import com.services.post.dto.response.PostResponseDto;
import com.services.post.exception.ErrorCode;
import com.services.post.exception.ServiceException;
import com.services.post.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PostController {

    private final PostService postService;

    private final PostConverter postConverter;

    @Autowired
    public PostController(PostService postService, PostConverter postConverter) {
        this.postService = postService;
        this.postConverter = postConverter;
    }

    private static final String GREETING_MESSAGE = "Hello, k8s!";

    @GetMapping("/greeting")
    public String getGreetingMessage() {
        return GREETING_MESSAGE;
    }

    @PostMapping("/posts")
    public PostResponseDto create(@RequestBody @Valid PostRequestDto postRequestDto) {
        Post post = postConverter.convertToPost(postRequestDto);
        post = postService.create(post);
        return postConverter.convertToPostResponseDto(post);
    }

    @GetMapping("/posts/{id}")
    public PostResponseDto getById(@PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);

        if (postOptional.isEmpty()) {
            throw new ServiceException(ErrorCode.POST_NOT_FOUND);
        }

        return postConverter.convertToPostResponseDto(postOptional.get());
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (!postService.existsById(id)) {
            throw new ServiceException(ErrorCode.POST_NOT_FOUND);
        }

        postService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/posts/{id}")
    public PostResponseDto update(@PathVariable Long id, @RequestBody @Valid PostUpdateRequestDto postUpdateRequestDto) {
        if (!postService.existsById(id)) {
            throw new ServiceException(ErrorCode.POST_NOT_FOUND);
        }

        Optional<Post> post = postService.updateTextAndDateById(id, postUpdateRequestDto.getText());
        return postConverter.convertToPostResponseDto(post.get());
    }
}
