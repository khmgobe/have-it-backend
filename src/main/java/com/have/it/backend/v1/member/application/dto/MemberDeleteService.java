package com.have.it.backend.v1.member.application.dto;

import com.have.it.backend.v1.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberDeleteService {

    private final MemberRepository repository;

    public void deleteMember(Long memberId) {
        repository.deleteById(memberId);
    }
}
