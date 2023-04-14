package com.services.user.converter;

import com.services.user.domain.User;
import com.services.user.dto.request.UserRequestDto;
import com.services.user.dto.response.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User convertToUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        return user;
    }

    public UserResponseDto convertToUserResponseDto(User user) {
        return new UserResponseDto(user.getId(), user.getUsername(), user.getAmountOfPosts());
    }
}
