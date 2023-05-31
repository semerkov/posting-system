package com.services.post.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.services.post.databind.CustomLocalDateTimeSerializer;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostResponseDto {

    private long id;

    private long authorId;

    private String text;

    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime postedAt;

    private String topic;
}
