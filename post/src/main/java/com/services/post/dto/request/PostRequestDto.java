package com.services.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PostRequestDto {

    @NotNull
    @Positive
    private Long authorId;

    @NotBlank
    @Size(max = 1000)
    private String text;

    @Size(max = 100)
    private String topic;
}
