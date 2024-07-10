package com.have.it.backend.v1.member.domain.dto.request;


import com.have.it.backend.v1.member.domain.Member;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class MemberCreateRequestTest {

    @Test
    void 멤버_생성_요청_객체로_멤버_객체를_만들_수_있다()  {

        // given
        MemberCreateRequest request =
                MemberCreateRequest
                        .builder()
                        .email("test_email@naver.com")
                        .nickname("test_nickname")
                        .password("test_password")
                        .build();

        // when
        Member member = Member.fromCreate(request);

        // then
        assertThat(member)
                .extracting("email", "nickname", "password")
                .containsExactly(request.getEmail(), request.getNickname(), request.getPassword());
    }
}