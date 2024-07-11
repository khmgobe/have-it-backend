package com.have.it.backend.v1.member.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberCreateRequest {

    @NotBlank(message = "이메일은 비어있을 수 없습니다.")
    private final String email;

    @NotBlank(message = "닉네임은 비어있을 수 없습니다.")
    private final String nickname;

    @NotBlank(message = "비밀번호는 비어있을 수 없습니다.")
    private final String password;

    @Builder
    private MemberCreateRequest(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }
}
