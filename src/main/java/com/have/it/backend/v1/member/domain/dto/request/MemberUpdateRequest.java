package com.have.it.backend.v1.member.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberUpdateRequest {

    @NotBlank(message = "닉네임은 비어있을 수 없습니다")
    private final String nickname;

    @Builder
    @JsonCreator
    private MemberUpdateRequest(String nickname) {
        this.nickname = nickname;
    }
}
