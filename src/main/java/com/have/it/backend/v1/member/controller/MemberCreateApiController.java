package com.have.it.backend.v1.member.controller;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.member.service.MemberCreateService;
import com.have.it.backend.v1.member.domain.dto.request.MemberCreateRequest;
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
    public ResponseEntity<BaseResponse<Void>> createMember(@RequestBody MemberCreateRequest memberCreateRequest) {

        service.createMember(memberCreateRequest);

        return ResponseEntity.ok(BaseResponse.created());
    }
}
