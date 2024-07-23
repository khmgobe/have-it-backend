package com.have.it.backend.v1.member.service;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.member.domain.Member;
import com.have.it.backend.v1.member.dto.request.MemberUpdateRequest;
import com.have.it.backend.v1.member.repository.MemberJpaRepository;
import com.have.it.backend.v1.member.service.usecase.MemberUpdateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberUpdateService implements MemberUpdateUseCase {


    private final MemberJpaRepository repository;

    public void updateMember(final Long memberId, final MemberUpdateRequest request) {

        final Member member = repository
                .findById(memberId)
                .orElseThrow(() -> new BaseException(ExceptionInformation.ID_NO_CONTENT));

        member.updateNickname(request.nickname());
        member.updatePassword(request.password());
    }
}
