package com.have.it.backend.v1.member.domain.dto.response;

import com.have.it.backend.v1.member.domain.Member;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemberReadResponseTest {

    @Test
    void 멤버_객체로_멤버_조회_응답_객체를_만들_수_있다()  {

        // given
        Member member = Member
                .builder()
                .id(1L)
                .nickname("test_nickname")
                .email("test_email@naver.com")
                .build();

        // when
        MemberReadResponse response = MemberReadResponse.fromModel(member);

        // then
        assertThat(response)
                .extracting("id", "nickname", "email")
                .containsExactly(member.getId(), member.getNickname(), member.getEmail());
    }

}