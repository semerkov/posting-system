package com.services.user.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AmountOfPostsChangeRequestDto {

    @NotNull
    private Integer amountChange;
}
