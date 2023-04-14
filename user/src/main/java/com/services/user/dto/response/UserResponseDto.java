package com.services.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private long id;

    private String username;

    private int amountOfPosts;
}
