package com.have.it.backend.v1.member.service;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.member.domain.Member;
import com.have.it.backend.v1.member.infrastructure.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberUpdateService {


    private final MemberJpaRepository repository;

    public void updateMember(final Long memberId, final String nickname) {

        Member member = findMemberById(memberId);
        member.updateNickname(nickname);
    }

    private Member findMemberById(final Long memberId){

        return repository
                .findById(memberId)
                .orElseThrow(
                        () -> new BaseException(ExceptionInformation.ID_NO_CONTENT)).toModel();
    }
}
