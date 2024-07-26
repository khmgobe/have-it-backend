package com.have.it.backend.v1.member.service;

import static com.have.it.backend.v1.common.util.enumeration.ExceptionInformation.*;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.member.domain.Member;
import com.have.it.backend.v1.member.dto.response.MemberReadResponse;
import com.have.it.backend.v1.member.repository.MemberJpaRepository;
import com.have.it.backend.v1.member.service.usecase.MemberReadUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberReadService implements MemberReadUseCase {

    private final MemberJpaRepository repository;

    @Override
    public MemberReadResponse findMemberById(final Long memberId) {

        final Member member =
                repository.findById(memberId).orElseThrow(() -> new BaseException(ID_NO_CONTENT));

        return MemberReadResponse.toResponse(member);
    }

    @Override
    public List<MemberReadResponse> findAllMember() {

        return repository.findAll().stream().map(MemberReadResponse::toResponse).toList();
    }
}
