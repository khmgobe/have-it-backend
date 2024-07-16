package com.have.it.backend.v1.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record MemberUpdateRequest (
                    @NotBlank(message = "닉네임은 비어있을 수 없습니다.")
                    String nickname) {

    public MemberUpdateRequest toServiceRequest() {
        return MemberUpdateRequest
                .builder()
                .nickname(nickname)
                .build();
    }
}