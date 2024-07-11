package com.have.it.backend.v1.member.controller;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.member.service.MemberCreateService;
import com.have.it.backend.v1.member.domain.dto.request.MemberCreateRequest;
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

    private final MemberCreateService service;

    @PostMapping("/api/v1/member/register")
    public ResponseEntity<BaseResponse<Void>> createMember(@Valid @RequestBody MemberCreateRequest request) {

        service.registerMember(request.getEmail(), request.getNickname(), request.getPassword());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BaseResponse.created());
    }
}
