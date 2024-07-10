package com.have.it.backend.v1.post.domain;

import com.have.it.backend.v1.common.util.BaseTimeEntity;
import com.have.it.backend.v1.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member writer;

    @Builder
    private Post(Long id, String title, String content, Member writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public static Post of(String title, String content, Member member) {
        return Post
                .builder()
                .title(title)
                .content(content)
                .writer(member)
                .build();
    }

     public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
