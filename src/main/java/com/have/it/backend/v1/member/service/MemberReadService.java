package com.have.it.backend.v1.member.service;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.member.domain.dto.response.MemberReadResponse;
import com.have.it.backend.v1.member.domain.Member;
import com.have.it.backend.v1.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static com.have.it.backend.v1.common.util.enumeration.ExceptionInformation.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberReadService {

    private final MemberJpaRepository repository;

    public MemberReadResponse findMemberById(final Long memberId) {

        final Member member = repository
                .findById(memberId)
                .orElseThrow(() -> new BaseException(ID_NO_CONTENT));

        return MemberReadResponse.fromModel(member);
    }

    public List<MemberReadResponse> findAllMember() {

        return repository
                .findAll()
                .stream()
                .map(MemberReadResponse::fromModel)
                .toList();
    }
}