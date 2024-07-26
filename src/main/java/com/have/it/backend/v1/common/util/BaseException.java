package com.have.it.backend.v1.common.util;

import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final ExceptionInformation exceptionInformation;

    public BaseException(ExceptionInformation exceptionInformation) {
        super();
        this.exceptionInformation = exceptionInformation;
    }

    public int getStatus() {
        return exceptionInformation.getStatus();
    }

    @Override
    public String getMessage() {
        return exceptionInformation.getMessage();
    }
}
