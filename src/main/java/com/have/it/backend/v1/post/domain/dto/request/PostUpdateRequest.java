package com.have.it.backend.v1.post.domain.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostUpdateRequest {

    private final String title;
    private final String content;

    @Builder
    private PostUpdateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
