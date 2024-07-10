package com.have.it.backend.v1.member.domain.dto.response;

import com.have.it.backend.v1.member.domain.Member;
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

    public static MemberReadResponse fromModel(Member member) {
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
