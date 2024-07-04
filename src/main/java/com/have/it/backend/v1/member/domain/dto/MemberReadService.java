package com.have.it.backend.v1.member.domain.dto;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.member.domain.dto.response.MemberReadResponse;
import com.have.it.backend.v1.member.domain.Member;
import com.have.it.backend.v1.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.have.it.backend.v1.common.util.enumeration.ExceptionInformation.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberReadService {

    private final MemberRepository repository;

    public MemberReadResponse readMember(final Long memberId) {

        Member member = repository
                .findById(memberId)
                .orElseThrow(() -> new BaseException(ID_NOT_FOUND));

        return MemberReadResponse.from(member);
    }
}
