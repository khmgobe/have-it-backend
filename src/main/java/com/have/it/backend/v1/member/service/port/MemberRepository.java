package com.have.it.backend.v1.member.service.port;

import com.have.it.backend.v1.member.domain.Member;
import java.util.Optional;

public interface MemberRepository {

    boolean existsByEmail(String email);

    Member save(Member member);

    void deleteById(Long memberId);

    Optional<Member> findById(Long memberId);

}
