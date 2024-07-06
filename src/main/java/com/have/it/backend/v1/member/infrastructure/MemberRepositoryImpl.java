package com.have.it.backend.v1.member.infrastructure;

import com.have.it.backend.v1.member.domain.Member;
import com.have.it.backend.v1.member.service.port.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public boolean existsByEmail(String email) {
        return memberJpaRepository.existsByEmail(email);
    }

    @Override
    public Member save(Member member) {
        return memberJpaRepository
                .save(MemberEntity.fromModel(member))
                .toModel();
    }

    @Override
    public void deleteById(Long memberId) {
        memberJpaRepository.deleteById(memberId);
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return memberJpaRepository
                .findById(memberId)
                .map(MemberEntity::toModel);
    }
}
