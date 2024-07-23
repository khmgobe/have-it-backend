package com.have.it.backend.member.repository;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.member.domain.Member;
import com.have.it.backend.v1.member.repository.MemberJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @BeforeEach
    void setUp() {
        memberJpaRepository.deleteAllInBatch();
    }

    @Test
    void 멤버를_정상적으로_저장할_수_있어야_하고_저장한_멤버를_확인할_수_있어야_한다()  {

        // given
        Member member = Member
                .builder()
                .email("test_email")
                .nickname("test_nickname")
                .password("test_password")
                .build();

        // when
        Member savedMember = memberJpaRepository.save(member);

        memberJpaRepository
                .findById(savedMember.getId())
                .orElseThrow(() -> new BaseException(ExceptionInformation.ID_NO_CONTENT));

        // then
        assertThat(savedMember)
                .extracting("email","nickname","password")
                .containsExactly(member.getEmail(), member.getNickname(), member.getPassword());
    }

    @Test
    void 멤버를_정상적으로_삭제할_수_있어야_한다() {

        // given
        Member member = Member
                .builder()
                .email("test_email")
                .nickname("test_nickname")
                .password("test_password")
                .build();

        // when
        memberJpaRepository.save(member);

        // then
        memberJpaRepository.deleteById(member.getId());
        Optional<Member> findMember = memberJpaRepository.findById(member.getId());

        // then
        assertThat(findMember.isEmpty());
    }

    @Test
    void 저장한_멤버_목록을_정상적으로_조회할_수_있어야_한다()  {

        // given
        List<Member> memberList = List.of(

                Member.builder()
                        .email("test_email")
                        .nickname("test_nickname")
                        .password("test_password")
                        .build(),

                Member.builder()
                        .email("test_email2")
                        .nickname("test_nickname2")
                        .password("test_password2")
                        .build());

        //when
        memberJpaRepository.saveAll(memberList);

        // then
        List<Member> findAllMembers = memberJpaRepository.findAll();
        assertThat(findAllMembers).hasSize(2)
                .extracting("email", "nickname", "password")
                .containsExactlyInAnyOrder(
                        tuple("test_email", "test_nickname", "test_password"),
                        tuple("test_email2", "test_nickname2", "test_password2")
                );
    }
}