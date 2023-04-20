package com.services.post.client;

import com.services.post.dto.response.AmountOfPostsChangeResponseDto;
import com.services.post.dto.response.AmountOfPostsResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class UserServiceClient {

    private final String userServiceUrl;

    private final WebClient client;

    @Autowired
    public UserServiceClient(@Value("${user-service.url}") String userServiceUrl) {
        this.userServiceUrl = userServiceUrl;

        client = WebClient.builder()
                .baseUrl(userServiceUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public void updateAmountOfPosts(long authorId, int amountChange) {
        AmountOfPostsChangeResponseDto responseDto = new AmountOfPostsChangeResponseDto(amountChange);

        client.put()
                .uri("/users/{0}/amount-of-posts/change", authorId)
                .body(Mono.just(responseDto), AmountOfPostsChangeResponseDto.class)
                .exchangeToMono(response -> {
                    HttpStatusCode responseStatus = response.statusCode();
                    log.info("Changing the amount of posts. Status: {}.", responseStatus);

                    if (responseStatus.equals(HttpStatus.OK)) {
                        return response.bodyToMono(AmountOfPostsResponseDto.class);
                    } else {
                        log.error("Error during changing the amount of posts.");
                        return response.createError();
                    }
                })
                .subscribe(response -> log.info("Update was successfully executed for author with id {}. Result amount {}",
                        authorId,
                        response.getAmountOfPosts()));
    }
}
