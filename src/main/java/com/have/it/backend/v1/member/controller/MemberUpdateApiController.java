package com.have.it.backend.v1.member.controller;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.member.dto.request.MemberUpdateRequest;
import com.have.it.backend.v1.member.service.usecase.MemberUpdateUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "MEMBER-UPDATE", description = "멤버 수정 관련 API ")
public class MemberUpdateApiController {

    private final MemberUpdateUseCase memberUpdateUseCase;

    @PatchMapping(path = "api/v1/member/update/{memberId}")
    public ResponseEntity<BaseResponse<Long>> updateMember(
            @PathVariable Long memberId, @Valid @RequestBody MemberUpdateRequest memberUpdateRequest) {

        memberUpdateUseCase.updateMember(memberId, memberUpdateRequest.toServiceRequest());

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.success(memberId));
    }
}
