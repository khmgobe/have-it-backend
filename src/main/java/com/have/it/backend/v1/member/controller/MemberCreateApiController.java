package com.have.it.backend.v1.member.controller;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.member.domain.dto.MemberCreateService;
import com.have.it.backend.v1.member.domain.dto.MemberCreateRequest;
import com.have.it.backend.v1.member.domain.dto.response.MemberCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberCreateApiController {

    private final MemberCreateService service;

    @PostMapping("/api/v1/member/register")
    public ResponseEntity<BaseResponse<MemberCreateResponse>> createMember(@RequestBody MemberCreateRequest memberCreateRequest) {

        service.createMember(memberCreateRequest);

        return ResponseEntity.ok(BaseResponse.created());
    }
}
