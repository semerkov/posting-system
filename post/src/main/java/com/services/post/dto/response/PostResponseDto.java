package com.services.post.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.services.post.databind.CustomLocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostResponseDto {

    private long id;

    private long authorId;

    private String text;

    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime postedAt;
}
