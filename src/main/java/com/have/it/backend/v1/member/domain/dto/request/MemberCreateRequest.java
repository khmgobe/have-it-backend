package com.have.it.backend.v1.member.domain.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberCreateRequest {

    private final String email;

    private final String nickname;

    private final String password;

    @Builder
    private MemberCreateRequest(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }
}
