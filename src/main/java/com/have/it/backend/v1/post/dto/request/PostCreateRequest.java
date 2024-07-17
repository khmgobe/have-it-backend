package com.have.it.backend.v1.post.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostCreateRequest {

    private final Long memberId;

    private final String title;

    private final String content;
}
