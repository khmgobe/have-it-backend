package com.have.it.backend.v1.member.service;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.member.domain.Member;
import com.have.it.backend.v1.member.domain.dto.request.MemberCreateRequest;
import com.have.it.backend.v1.member.service.port.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCreateService {

    private final MemberRepository repository;

    public void registerMember(final MemberCreateRequest memberCreateRequest) {

        final Member member = Member.of(memberCreateRequest.getEmail(), memberCreateRequest.getNickname(), memberCreateRequest.getPassword());

        validateDuplicateEmail(member.getEmail());

        repository.save(member);

    }

    private void validateDuplicateEmail(String email) {

        if(repository.existsByEmail(email)) {
            throw new BaseException(ExceptionInformation.MEMBER_EMAIL_DUPLICATED);
        }
    }
}