package com.services.post.service;

import com.services.post.client.UserServiceClient;
import com.services.post.domain.Post;
import com.services.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    private final UserServiceClient userServiceClient;

    @Autowired
    public PostService(PostRepository postRepository, UserServiceClient userServiceClient) {
        this.postRepository = postRepository;
        this.userServiceClient = userServiceClient;
    }

    @Transactional(readOnly = true)
    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsById(long id) {
        return postRepository.existsById(id);
    }

    @Transactional
    public Post create(Post post) {
        post = postRepository.save(post);
        updateNumberOfPosts(post.getAuthorId(), 1);
        return post;
    }

    @Transactional
    public void deleteById(long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            postRepository.deleteById(id);
            updateNumberOfPosts(post.getAuthorId(), -1);
        }
    }

    @Transactional
    public Optional<Post> updateTextAndDateById(long id, String text) {
        postRepository.updateTextAndDateById(id, text, LocalDateTime.now());
        return postRepository.findById(id);
    }

    private void updateNumberOfPosts(long authorId, int amountChange) {
        userServiceClient.updateAmountOfPosts(authorId, amountChange);
    }
}
