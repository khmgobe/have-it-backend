package com.have.it.backend.v1.post.dto.response;

import com.have.it.backend.v1.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostReadResponse {

    private final Long id;
    private final Long memberId;
    private final String title;
    private final String content;

    @Builder
    private PostReadResponse(Long id, Long memberId, String title, String content) {
        this.id = id;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
    }

    public static PostReadResponse from(Post post) {

        return PostReadResponse
                .builder()
                .id(post.getId())
                .memberId(post.getWriter().getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }
}
