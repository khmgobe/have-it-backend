package com.have.it.backend.v1.member.domain;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {


    private static final int MAX_NICKNAME_LENGTH = 15;
    private static final int MAX_EMAIL_LENGTH = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;


    @Builder
    private Member(Long id, String email, String nickname, String password) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public void updateNickname(final String nickname) {
        validateNickname(nickname);
        this.nickname = nickname;
    }

    private void validateNickname(final String nickname) {
        if(Objects.isNull(nickname) || nickname.length() > MAX_NICKNAME_LENGTH) {
            throw new BaseException(ExceptionInformation.MEMBER_NICKNAME_ILLEGAL_LENGTH);
        }
    }
}
