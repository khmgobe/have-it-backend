package com.have.it.backend.v1.post.dto.response;

import com.have.it.backend.v1.post.domain.Post;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostReadResponse {

    private final Long id;
    private final Long memberId;
    private final String memberNickname;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    @Builder
    private PostReadResponse(
            Long id,
            Long memberId,
            String memberNickname,
            String title,
            String content,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt) {
        this.id = id;
        this.memberId = memberId;
        this.memberNickname = memberNickname;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static PostReadResponse toResponse(Post post) {

        return PostReadResponse.builder()
                .id(post.getId())
                .memberId(post.getWriter().getId())
                .memberNickname(post.getWriter().getNickname())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .build();
    }
}
