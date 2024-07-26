package com.have.it.backend.v1.member.domain;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.BaseTimeEntity;
import com.have.it.backend.v1.common.util.RegexUtil;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.member.domain.enumeration.Role;
import jakarta.persistence.*;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    private static final String VALID_EMAIL_URL_REGEX = "^[a-z0-9]+@[a-z]+\\.[a-z]{2,}$";
    private static final int MAX_NICKNAME_LENGTH = 15;
    private static final int MAX_PASSWORD_LENGTH = 15;
    private static final int MAX_EMAIL_LENGTH = 30;

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = MAX_NICKNAME_LENGTH)
    private String email;

    @Column(nullable = false, length = MAX_NICKNAME_LENGTH, unique = true)
    private String nickname;

    @Column(nullable = false, length = MAX_PASSWORD_LENGTH)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Builder
    private Member(String email, String nickname, String password, Role role) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.role = role;
    }

    public static Member fromCreate(String email, String nickname, String password, Role role) {

        validateEmail(email);
        validateNickname(nickname);
        validatePassword(password);
        validateRole(role);

        return Member.builder().email(email).nickname(nickname).password(password).role(role).build();
    }

    public void updateNickname(final String nickname) {
        validateNickname(nickname);

        this.nickname = nickname;
    }

    public void updatePassword(final String password) {
        validatePassword(password);

        this.password = password;
    }

    public boolean isMember() {
        return getRole() == Role.MEMBER;
    }

    public boolean isAdmin() {
        return getRole() == Role.ADMIN;
    }

    // inner method
    private static void validateNickname(final String nickname) {
        if (Objects.isNull(nickname)) {
            throw new BaseException(ExceptionInformation.NICKNAME_ILLEGAL_NULL);
        }

        if (nickname.isBlank() || nickname.length() > MAX_NICKNAME_LENGTH) {
            throw new BaseException(ExceptionInformation.NICKNAME_ILLEGAL_LENGTH);
        }
    }

    private static void validateEmail(final String email) {
        if (Objects.isNull(email) || email.length() > MAX_EMAIL_LENGTH) {
            throw new BaseException(ExceptionInformation.EMAIL_ILLEGAL_LENGTH);
        }

        if (!RegexUtil.matches(VALID_EMAIL_URL_REGEX, email)) {
            throw new BaseException(ExceptionInformation.EMAIL_ILLEGAL_PATTERN);
        }
    }

    private static void validateRole(Role role) {
        if (Objects.isNull(role)) {
            throw new BaseException(ExceptionInformation.ROLE_ILLEGAL_NULL);
        }
    }

    private static void validatePassword(final String password) {
        if (Objects.isNull(password) || password.length() > MAX_PASSWORD_LENGTH) {
            throw new BaseException(ExceptionInformation.PASSWORD_ILLEGAL_LENGTH);
        }
    }
}
