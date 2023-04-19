package com.services.post.repository;

import com.services.post.domain.Post;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    @Modifying
    @Query(value = "UPDATE posts SET text = :text, posted_at = :postedAt WHERE id = :id", nativeQuery = true)
    void updateTextAndDateById(@Param("id") long id, @Param("text") String text, @Param("postedAt") LocalDateTime postedAt);
}
