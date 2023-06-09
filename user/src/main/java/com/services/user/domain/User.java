package com.services.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    /**
     * User identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    /**
     * Username
     */
    @Column(length = 255, nullable = false, unique = true)
    private String username;


    /**
     * Amount of posts for the user
     */
    @Column(nullable = false)
    private int amountOfPosts;
}
