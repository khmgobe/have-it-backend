package com.have.it.backend.v1.common.util.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionInformation {


    ID_NOT_FOUND("아이디가 존재하지 않습니다", HttpStatus.NOT_FOUND.value()),
    MEMBER_NICKNAME_ILLEGAL_LENGTH("올바르지 않은 닉네임 길이입니다.", HttpStatus.BAD_REQUEST.value());

    private final String message;
    private final Integer status;
}
