package com.services.user.controller;

import com.services.user.converter.UserConverter;
import com.services.user.domain.User;
import com.services.user.dto.request.UserRequestDto;
import com.services.user.dto.response.UserResponseDto;
import com.services.user.exception.ErrorCode;
import com.services.user.exception.ServiceException;
import com.services.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    private final UserConverter userConverter;

    @Autowired
    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    private static final String GREETING_MESSAGE = "Hello, k8s!";

    @GetMapping("/greeting")
    public String getGreetingMessage() {
        return GREETING_MESSAGE;
    }

    @PostMapping("/users")
    public UserResponseDto create(@RequestBody @Valid UserRequestDto userRequestDto) {
        if (userService.existsByUsername(userRequestDto.getUsername())) {
            throw new ServiceException(ErrorCode.USERNAME_IS_TAKEN);
        }

        User user = userConverter.convertToUser(userRequestDto);
        user = userService.create(user);
        return userConverter.convertToUserResponseDto(user);
    }

    @GetMapping("/users/{id}")
    public UserResponseDto getById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isEmpty()) {
            throw new ServiceException(ErrorCode.USER_NOT_FOUND);
        }

        return userConverter.convertToUserResponseDto(userOptional.get());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (!userService.existsById(id)) {
            throw new ServiceException(ErrorCode.USER_NOT_FOUND);
        }

        userService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{id}")
    public UserResponseDto update(@PathVariable Long id, @RequestBody @Valid UserRequestDto userRequestDto) {
        if (!userService.existsById(id)) {
            throw new ServiceException(ErrorCode.USER_NOT_FOUND);
        }

        if (userService.existsAnotherUserWithUsername(userRequestDto.getUsername(), id)) {
            throw new ServiceException(ErrorCode.USERNAME_IS_TAKEN);
        }

        Optional<User> userOptional = userService.updateUsernameById(id, userRequestDto.getUsername());
        return userConverter.convertToUserResponseDto(userOptional.get());
    }
}
