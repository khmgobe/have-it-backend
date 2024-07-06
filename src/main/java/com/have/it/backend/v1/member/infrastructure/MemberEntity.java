package com.have.it.backend.v1.member.infrastructure;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity {


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
    private MemberEntity(Long id, String email, String nickname, String password) {
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

    public static MemberEntity fromModel(Member member) {
        return MemberEntity
                .builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .password(member.getPassword())
                .build();
    }

    public Member toModel() {
        return Member
                .builder()
                .id(id)
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
    }
}
