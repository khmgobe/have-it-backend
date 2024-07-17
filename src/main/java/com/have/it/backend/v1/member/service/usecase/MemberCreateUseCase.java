package com.have.it.backend.v1.member.service.usecase;

import com.have.it.backend.v1.member.dto.request.MemberCreateRequest;

public interface MemberCreateUseCase {

    void registerMember(MemberCreateRequest serviceRequest);
}
