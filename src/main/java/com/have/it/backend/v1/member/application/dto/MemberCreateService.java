package com.have.it.backend.v1.member.application.dto;

import com.have.it.backend.v1.member.application.dto.request.MemberCreateRequest;
import com.have.it.backend.v1.member.domain.Member;
import com.have.it.backend.v1.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCreateService {

    private final MemberRepository repository;

    public void createMember(final MemberCreateRequest memberCreateRequest) {

        final Member member = Member
                .builder()
                .email(memberCreateRequest.getEmail())
                .nickname(memberCreateRequest.getNickname())
                .password(memberCreateRequest.getPassword())
                .build();

        repository.save(member);

    }
}
