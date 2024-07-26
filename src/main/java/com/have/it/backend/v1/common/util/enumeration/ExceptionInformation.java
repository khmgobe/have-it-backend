package com.have.it.backend.v1.common.util.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionInformation {

    /* 204: NO_CONTENT */
    ID_NO_CONTENT("아이디가 존재하지 않습니다", HttpStatus.NOT_FOUND.value()),

    /* 400: BAD_REQUEST */
    ID_OR_PASSWORD_ILLEGAL_TEXT("아이디 또는 비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST.value()),
    NICKNAME_ILLEGAL_LENGTH("올바르지 않은 닉네임 길이입니다.", HttpStatus.BAD_REQUEST.value()),
    NICKNAME_ILLEGAL_NULL("올바르지 않은 닉네임 값입니다.", HttpStatus.BAD_REQUEST.value()),
    ROLE_ILLEGAL_NULL("올바르지 않은 권한 값입니다.", HttpStatus.BAD_REQUEST.value()),
    EMAIL_ILLEGAL_LENGTH("올바르지 않은 이메일 길이입니다.", HttpStatus.BAD_REQUEST.value()),
    EMAIL_ILLEGAL_PATTERN("올바르지 않은 이메일 패턴입니다.", HttpStatus.BAD_REQUEST.value()),
    PASSWORD_ILLEGAL_LENGTH("올바르지 않은 비밀번호 길이입니다.", HttpStatus.BAD_REQUEST.value()),

    /* 409: CONFLICT */
    EMAIL_DUPLICATED("이메일이 중복되었습니다.", HttpStatus.CONFLICT.value()),
    FOLDER_TITLE_DUPLICATED("폴더 제목이 중복되었습니다.", HttpStatus.CONFLICT.value());

    private final String message;
    private final Integer status;
}
