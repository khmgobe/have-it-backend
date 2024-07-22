package com.have.it.backend.v1.member.controller;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.member.service.usecase.MemberDeleteUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "MEMBER-DELETE", description = "멤버 삭제 관련 API ")

public class MemberDeleteApiController {

    private final MemberDeleteUseCase memberDeleteUseCase;


    @DeleteMapping(path = "/api/v1/member/delete/{memberId}")
    public ResponseEntity<BaseResponse<Long>> deleteMember (@PathVariable Long memberId) {

        memberDeleteUseCase.deleteMember(memberId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(memberId));
    }
}
