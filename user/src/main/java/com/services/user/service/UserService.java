package com.services.user.service;

import com.services.user.domain.User;
import com.services.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public boolean existsById(long id) {
        return userRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User create(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsAnotherUserWithUsername(String username, long id) {
        return userRepository.existsAnotherUserWithUsername(username, id);
    }

    @Transactional
    public Optional<User> updateUsernameById(long id, String username) {
        userRepository.updateUsernameById(id, username);
        return userRepository.findById(id);
    }

    @Transactional
    public int updateAmountOfPostsById(long id, int amountChange) {
        userRepository.updateAmountOfPostsById(id, amountChange);
        return userRepository.getAmountOfPostsById(id);
    }
}
