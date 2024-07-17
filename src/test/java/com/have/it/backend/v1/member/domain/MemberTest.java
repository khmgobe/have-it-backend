package com.have.it.backend.v1.member.domain;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {

    @Test
    void 멤버_닉네임을_변경하고_변경전_데이터랑_정상적으로_비교할_수_있어야_한다()  {

        // given
        Member member = Member
                .builder()
                .email("test_email")
                .nickname("test_nickname")
                .password("test_password")
                .build();

        //when
        member.updateNickname("test_nickname2");

        // then
        assertThat(member.getNickname()).isEqualTo("test_nickname2");
    }

    @Test
    void 멤버_닉네임을_변경할_때_정해진_길이를_초과했을_시_예외를_발생시켜야_한다()  {

        // given
        Member member = Member
                .builder()
                .email("test_email")
                .nickname("test_nickname")
                .password("test_password")
                .build();

        //when // then
        assertThatThrownBy(() -> member.updateNickname("test_nicknameaaaaaaa"))
                .isInstanceOf(BaseException.class)
                .hasMessage(ExceptionInformation.NICKNAME_ILLEGAL_LENGTH.getMessage());
    }
}
