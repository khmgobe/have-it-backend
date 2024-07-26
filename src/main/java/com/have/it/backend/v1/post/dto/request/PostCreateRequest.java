package com.have.it.backend.v1.post.dto.request;

import com.have.it.backend.v1.member.domain.Member;
import com.have.it.backend.v1.post.domain.Post;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record PostCreateRequest(
        @Min(value = 1, message = "멤버 아이디는 1 이상 이어야 합니다.") Long memberId,
        @NotBlank(message = "제목은 비어있을 수 없습니다.") String title,
        @NotBlank(message = "내용은 비어있을 수 없습니다.") String content) {

    public static Post createPost(Member member, String title, String content) {
        return Post.builder().writer(member).title(title).content(content).build();
    }

    public PostCreateRequest toServiceRequest() {
        return PostCreateRequest.builder().memberId(memberId).title(title).content(content).build();
    }
}
