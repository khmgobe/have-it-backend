package com.have.it.backend.v1.member.domain.dto;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.member.domain.Member;
import com.have.it.backend.v1.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberUpdateService {


    private final MemberRepository repository;

    public void updateMember(Long memberId, MemberUpdateRequest memberUpdateRequest) {
        Member member = findMemberById(memberId);
        member.updateNickname(memberUpdateRequest.getNickname());

    }

    private Member findMemberById(final Long memberId){
        return repository.findById(memberId)
                .orElseThrow(() -> new BaseException(ExceptionInformation.ID_NOT_FOUND));
    }
}
