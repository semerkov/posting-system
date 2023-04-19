package com.services.post.service;

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

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
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
        return postRepository.save(post);
    }

    @Transactional
    public void deleteById(long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public Optional<Post> updateTextAndDateById(long id, String text) {
        postRepository.updateTextAndDateById(id, text, LocalDateTime.now());
        return postRepository.findById(id);
    }
}
