package com.have.it.backend.v1.member.service;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.member.repository.MemberJpaRepository;
import com.have.it.backend.v1.member.service.usecase.MemberDeleteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberDeleteService implements MemberDeleteUseCase {

    private final MemberJpaRepository repository;

    @Override
    public void deleteMember(final Long memberId) {

        repository.findById(memberId)
                  .orElseThrow(() -> new BaseException(ExceptionInformation.ID_NO_CONTENT));

        repository.deleteById(memberId);

    }
}
