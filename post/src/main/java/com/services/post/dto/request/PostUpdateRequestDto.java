package com.services.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PostUpdateRequestDto {

    @NotBlank
    @Size(max = 1000)
    private String text;
}
