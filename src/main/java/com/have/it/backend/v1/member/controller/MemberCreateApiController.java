package com.have.it.backend.v1.member.controller;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.member.dto.request.MemberCreateRequest;
import com.have.it.backend.v1.member.service.usecase.MemberCreateUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberCreateApiController {

    private final MemberCreateUseCase memberCreateUseCase;

    @PostMapping("/api/v1/member/register")
    public ResponseEntity<BaseResponse<Void>> createMember(@Valid @RequestBody MemberCreateRequest request) {

        memberCreateUseCase.registerMember(request.toServiceRequest());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BaseResponse.created());
    }
}
