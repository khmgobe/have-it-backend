package com.have.it.backend.v1.member.domain.dto.response;

import com.have.it.backend.v1.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberReadResponse {

    private final String nickname;
    private final String email;


    @Builder
    private MemberReadResponse(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }

    public static MemberReadResponse from(Member member) {
        return MemberReadResponse
                .builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }
}
