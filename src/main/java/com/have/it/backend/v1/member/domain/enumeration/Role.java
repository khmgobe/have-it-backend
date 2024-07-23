package com.have.it.backend.v1.member.domain.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("관리자"), MEMBER("일반 사용자"), GUEST("손님");

    private final String detail;
}
