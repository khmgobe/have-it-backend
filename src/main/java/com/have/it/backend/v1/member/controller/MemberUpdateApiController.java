package com.have.it.backend.v1.member.controller;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.member.domain.dto.request.MemberUpdateRequest;
import com.have.it.backend.v1.member.service.MemberUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberUpdateApiController {

    private final MemberUpdateService service;

    @PatchMapping("api/v1/member/update/{memberId}")
    public ResponseEntity<BaseResponse<Long>> updateMember (@PathVariable Long memberId, @RequestBody MemberUpdateRequest memberUpdateRequest){

        service.updateMember(memberId, memberUpdateRequest.getNickname());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(memberId));
    }
}
