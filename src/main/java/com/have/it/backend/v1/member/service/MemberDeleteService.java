package com.have.it.backend.v1.member.service;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberDeleteService {

    private final MemberJpaRepository repository;

    public void deleteMember(Long memberId) {
        validateMemberId(memberId);
        repository.deleteById(memberId);
    }

    public void validateMemberId(Long memberId) {
        repository
                .findById(memberId)
                .orElseThrow(() -> new BaseException(ExceptionInformation.ID_NOT_FOUND));
    }
}
