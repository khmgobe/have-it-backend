package com.have.it.backend.v1.member.service;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.member.domain.Member;
import com.have.it.backend.v1.member.domain.dto.request.MemberCreateRequest;
import com.have.it.backend.v1.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCreateService {

    private final MemberJpaRepository repository;

    public void createMember(final MemberCreateRequest memberCreateRequest) {

        final Member member = Member
                .builder()
                .email(memberCreateRequest.getEmail())
                .nickname(memberCreateRequest.getNickname())
                .password(memberCreateRequest.getPassword())
                .build();

        validateDuplicateEmail(member.getEmail());

        repository.save(member);

    }

    private void validateDuplicateEmail(String email) {
        if(repository.existsByEmail(email)) {
            throw new BaseException(ExceptionInformation.MEMBER_EMAIL_DUPLICATED);
        }
    }
}