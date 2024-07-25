package com.have.it.backend.v1.member.dto.request;

import com.have.it.backend.v1.member.domain.enumeration.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record MemberCreateRequest(
        @NotBlank(message = "이메일은 비어있을 수 없습니다.") String email,
        @NotBlank(message = "닉네임은 비어있을 수 없습니다.") String nickname,
        @NotBlank(message = "비밀번호는 비어있을 수 없습니다.") String password,
        @NotNull(message = "권한은 비어있을 수 없습니다.") Role role) {

    public MemberCreateRequest toServiceRequest() {
        return MemberCreateRequest.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .role(role)
                .build();
    }
}
