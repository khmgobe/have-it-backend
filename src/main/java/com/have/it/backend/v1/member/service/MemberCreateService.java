package com.have.it.backend.v1.member.service;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.member.domain.Member;
import com.have.it.backend.v1.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCreateService {

    private final MemberJpaRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void registerMember(final String email, final String nickname, final String password) {

        final Member member = Member.of(email, nickname, passwordEncoder.encode(password));

        validateDuplicateEmail(member.getEmail());

        repository.save(member);

    }

    private void validateDuplicateEmail(String email) {

        if(repository.existsByEmail(email)) {
            throw new BaseException(ExceptionInformation.EMAIL_DUPLICATED);
        }
    }
}