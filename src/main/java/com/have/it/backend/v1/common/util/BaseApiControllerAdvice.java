package com.have.it.backend.v1.common.util;

import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BaseApiControllerAdvice {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<BaseResponse<Object>> bindException(BindException ex) {

        Map<String, List<String>> errorMap =
                ex.getBindingResult().getFieldErrors().stream()
                        .collect(
                                Collectors.groupingBy(
                                        FieldError::getField,
                                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));

        return ResponseEntity.badRequest().body(BaseResponse.badRequest(errorMap));
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse<ExceptionInformation>> baseException(BaseException ex) {

        ExceptionInformation exceptionInformation = ex.getExceptionInformation();

        return ResponseEntity.status(exceptionInformation.getStatus())
                .body(BaseResponse.badRequest(exceptionInformation));
    }
}
