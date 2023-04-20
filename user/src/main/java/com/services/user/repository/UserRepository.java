package com.services.user.repository;

import com.services.user.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByUsername(String username);

    @Query(value = "SELECT COUNT(id) != 0 FROM users WHERE username = :username AND id != :id", nativeQuery = true)
    boolean existsAnotherUserWithUsername(@Param("username") String username, @Param("id") long id);

    @Modifying
    @Query(value = "UPDATE users SET username = :username WHERE id = :id", nativeQuery = true)
    void updateUsernameById(@Param("id") long id, @Param("username") String username);

    @Modifying
    @Query(value = "UPDATE users SET amount_of_posts = amount_of_posts + :amountChange WHERE id = :id", nativeQuery = true)
    void updateAmountOfPostsById(@Param("id") long id, @Param("amountChange") int amountChange);

    @Query(value = "SELECT amount_of_posts FROM users WHERE id = :id", nativeQuery = true)
    int getAmountOfPostsById(@Param("id") long id);
}
