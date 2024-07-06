package com.have.it.backend.v1.member.domain.dto.response;

import com.have.it.backend.v1.member.domain.Member;
import com.have.it.backend.v1.member.infrastructure.member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberReadResponse {

    private final Long id;
    private final String nickname;
    private final String email;


    @Builder
    private MemberReadResponse(Long id, String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }

    public static MemberReadResponse from(Member member) {
        return MemberReadResponse
                .builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }
}
