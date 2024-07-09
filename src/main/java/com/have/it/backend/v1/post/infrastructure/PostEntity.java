package com.have.it.backend.v1.post.infrastructure;

import com.have.it.backend.v1.member.infrastructure.MemberEntity;
import com.have.it.backend.v1.post.domain.Post;
import com.have.it.backend.v1.post.domain.dto.response.PostReadResponse;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private MemberEntity writer;

    @Builder
    private PostEntity(Long id, String title, String content, MemberEntity writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public static PostEntity fromModel(Post post) {
        return PostEntity
                .builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .writer(MemberEntity.fromModel(post.getMember()))
                .build();
    }

    public Post toModel() {
        return Post
                .builder()
                .id(id)
                .title(title)
                .content(content)
                .member(writer.toModel())
                .build();
    }

    public PostReadResponse toResponse() {
        return PostReadResponse
                .builder()
                .id(id)
                .memberId(writer.getId())
                .title(title)
                .content(content)
                .build();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
