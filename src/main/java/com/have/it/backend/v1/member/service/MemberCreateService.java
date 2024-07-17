package com.have.it.backend.v1.member.service;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.member.domain.Member;
import com.have.it.backend.v1.member.dto.request.MemberCreateRequest;
import com.have.it.backend.v1.member.repository.MemberJpaRepository;
import com.have.it.backend.v1.member.service.usecase.MemberCreateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCreateService implements MemberCreateUseCase {

    private final MemberJpaRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerMember(final MemberCreateRequest request) {

        if(repository.existsByEmail(request.email())) {
            throw new BaseException(ExceptionInformation.EMAIL_DUPLICATED);
        }

        final Member member = Member.fromCreate(request.email(), request.nickname(), passwordEncoder.encode(request.password()));

        repository.save(member);
    }
}