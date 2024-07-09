package com.have.it.backend.v1.post.domain;

import com.have.it.backend.v1.member.domain.Member;
import com.have.it.backend.v1.post.domain.dto.request.PostCreateRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Post {

    private final Long id;
    private final String title;
    private final String content;
    private final Member member;


    @Builder
    private Post(Long id, String title, String content, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public static Post of(PostCreateRequest request, Member member) {
        return Post
                .builder()
                .title(request.getTitle())
                .content(request.getContent())
                .member(member)
                .build();
    }

    public void update(String title, String content) {

        Post.builder()
                .title(title)
                .content(content)
                .build();
    }
}