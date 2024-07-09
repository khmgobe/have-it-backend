package com.have.it.backend.v1.member.domain;

import com.have.it.backend.v1.member.domain.dto.request.MemberCreateRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {


    private final Long id;
    private final String email;
    private final String nickname;
    private final String password;


    @Builder
    private Member(Long id, String email, String nickname, String password) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public void updateNickname(String nickname) {
        Member
                .builder()
                .nickname(nickname)
                .build();
    }

    public static Member of(String email, String nickname, String password) {

        return Member.builder()
                .email(email)
                .nickname(nickname)
                .password(password).build();
    }
}
