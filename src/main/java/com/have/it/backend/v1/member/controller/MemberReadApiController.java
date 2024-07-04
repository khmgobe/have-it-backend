package com.have.it.backend.v1.member.controller;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.member.domain.dto.MemberReadService;
import com.have.it.backend.v1.member.domain.dto.response.MemberReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberReadApiController {

    private final MemberReadService service;

    @GetMapping("/api/v1/member/read/{memberId}")
    public ResponseEntity<BaseResponse<MemberReadResponse>> readMember(@PathVariable Long memberId) {

        service.readMember(memberId);

        return ResponseEntity
                .ok()
                .body(BaseResponse.success());
    }
}
