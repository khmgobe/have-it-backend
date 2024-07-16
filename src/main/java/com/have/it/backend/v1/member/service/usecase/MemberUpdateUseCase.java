package com.have.it.backend.v1.member.service.usecase;

import com.have.it.backend.v1.member.dto.request.MemberUpdateRequest;

public interface MemberUpdateUseCase {

    void updateMember(Long memberId, MemberUpdateRequest memberUpdateRequest);
}
