package com.have.it.backend.v1.member.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberUpdateRequest {

    private final String nickname;
}
