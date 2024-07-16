package com.have.it.backend.v1.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record MemberCreateRequest (
                    @NotBlank(message = "이메일은 비어있을 수 없습니다.")
                    String email,

                    @NotBlank(message = "닉네임은 비어있을 수 없습니다.")
                    String nickname,

                    @NotBlank(message = "비밀번호는 비어있을 수 없습니다.")
                    String password){


    public MemberCreateRequest toServiceRequest() {
        return MemberCreateRequest
                .builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
    }
}