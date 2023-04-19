package com.services.post.converter;

import com.services.post.domain.Post;
import com.services.post.dto.request.PostRequestDto;
import com.services.post.dto.response.PostResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PostConverter {

    public Post convertToPost(PostRequestDto postRequestDto) {
        Post post = new Post();
        post.setAuthorId(postRequestDto.getAuthorId());
        post.setText(postRequestDto.getText());
        post.setPostedAt(LocalDateTime.now());
        return post;
    }

    public PostResponseDto convertToPostResponseDto(Post post) {
        return new PostResponseDto(post.getId(),
                post.getAuthorId(),
                post.getText(),
                post.getPostedAt());
    }
}
