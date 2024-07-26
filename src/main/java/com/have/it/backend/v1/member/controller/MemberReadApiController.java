package com.have.it.backend.v1.member.controller;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.member.dto.response.MemberReadResponse;
import com.have.it.backend.v1.member.service.usecase.MemberReadUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "MEMBER-READ", description = "멤버 조회 관련 API ")
public class MemberReadApiController {

    private final MemberReadUseCase memberReadUseCase;

    @GetMapping(path = "/api/v1/member/read/{memberId}")
    public ResponseEntity<BaseResponse<MemberReadResponse>> findMemberById(
            @PathVariable Long memberId) {

        MemberReadResponse memberReadResponse = memberReadUseCase.findMemberById(memberId);

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.success(memberReadResponse));
    }

    @GetMapping(path = "/api/v1/member/read")
    public ResponseEntity<BaseResponse<List<MemberReadResponse>>> findAllMember() {

        List<MemberReadResponse> memberReadResponseList = memberReadUseCase.findAllMember();

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.success(memberReadResponseList));
    }
}
