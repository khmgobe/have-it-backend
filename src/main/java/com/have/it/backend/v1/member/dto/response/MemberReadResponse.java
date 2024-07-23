package com.have.it.backend.v1.member.dto.response;

import com.have.it.backend.v1.member.domain.Member;
import com.have.it.backend.v1.member.domain.enumeration.Role;
import lombok.Builder;

@Builder
public record MemberReadResponse (
                    Long id,
                    String nickname,
                    String email,
                    Role role)

{

    public static MemberReadResponse toResponse(Member member) {
        return MemberReadResponse
                .builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .role(member.getRole())
                .build();
    }
}
