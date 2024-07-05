package com.have.it.backend.v1.member.controller;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.member.service.MemberDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberDeleteApiController {

    private final MemberDeleteService service;


    @DeleteMapping("/api/v1/member/delete/{memberId}")
    public ResponseEntity<BaseResponse<Long>> deleteMember (@PathVariable Long memberId) {

        service.deleteMember(memberId);
        return ResponseEntity.ok().body(BaseResponse.success(memberId));
    }
}
