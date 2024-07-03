package com.have.it.backend.v1.member.ui;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.member.application.dto.MemberUpdateService;
import com.have.it.backend.v1.member.application.dto.request.MemberUpdateRequest;
import com.have.it.backend.v1.member.application.dto.response.MemberUpdateResponse;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<BaseResponse<MemberUpdateResponse>> updateMember (@PathVariable Long memberId, @RequestBody MemberUpdateRequest memberUpdateRequest){

        service.updateMember(memberId, memberUpdateRequest);

        return ResponseEntity.ok().body(BaseResponse.success());
    }
}
