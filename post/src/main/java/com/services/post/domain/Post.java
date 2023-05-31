package com.services.post.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {

    /**
     * Post identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    /**
     * Identifier of a user who created a post
     */
    @Column
    private long authorId;

    /**
     * Text of the post
     */
    @Column(length = 1000, nullable = false)
    private String text;

    /**
     * Date when the post was created
     */
    @Column(nullable = false)
    private LocalDateTime postedAt;

    /**
     * Message topic
     */
    @Column(length = 100)
    private String topic;
}
