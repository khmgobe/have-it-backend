package com.have.it.backend.v1.common.util;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BaseResponse<T> {

    private final T data;
    private final int status;

    @Builder
    private BaseResponse(T data, int status) {
        this.data = data;
        this.status = status;
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(data, 200);
    }

    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>(null, 200);
    }

    public static <T> BaseResponse<T> created() {
        return new BaseResponse<>(null, 201);
    }

    public static <T> BaseResponse<T> created(T data) {
        return new BaseResponse<>(data, 201);
    }

    public static <T> BaseResponse<T> badRequest(T data) {
        return new BaseResponse<>(data, 400);
    }

    public static <T> BaseResponse<T> internalServerError(T data) {
        return new BaseResponse<>(data, 500);
    }
}
