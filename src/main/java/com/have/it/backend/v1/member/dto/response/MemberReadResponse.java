package com.have.it.backend.v1.member.dto.response;

import com.have.it.backend.v1.member.domain.Member;
import lombok.Builder;

@Builder
public record MemberReadResponse (
                    Long id,
                    String nickname,
                    String email)
{

    public static MemberReadResponse toResponse(Member member) {
        return MemberReadResponse
                .builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }

    public Member toModel() {
        return Member
                .builder()
                .id(id)
                .email(email)
                .nickname(nickname)
                .build();
    }
}
