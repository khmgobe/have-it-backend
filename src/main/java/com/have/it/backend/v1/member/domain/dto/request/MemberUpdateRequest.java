package com.have.it.backend.v1.member.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class MemberUpdateRequest {

    private final String nickname;

    @JsonCreator
    private MemberUpdateRequest(String nickname) {
        this.nickname = nickname;
    }
}
