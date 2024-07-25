package com.have.it.backend.v1.member.service.usecase;

import com.have.it.backend.v1.member.dto.response.MemberReadResponse;
import java.util.List;

public interface MemberReadUseCase {

    MemberReadResponse findMemberById(Long memberId);

    List<MemberReadResponse> findAllMember();
}
