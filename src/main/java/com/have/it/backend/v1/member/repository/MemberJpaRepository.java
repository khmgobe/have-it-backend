package com.have.it.backend.v1.member.repository;

import com.have.it.backend.v1.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);
}