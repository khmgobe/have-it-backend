package com.have.it.backend.member.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.have.it.backend.util.ApiControllerTestSupport;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

class MemberDeleteApiControllerTest extends ApiControllerTestSupport {

    @Test
    @WithMockUser(value = "MEMBER")
    void 멤버를_삭제할_수_있다() throws Exception {

        // given
        Long memberId = 1L;

        willDoNothing().given(memberDeleteUseCase).deleteMember(anyLong());

        // when
        ResultActions actions =
                mockMvc
                        .perform(
                                delete("/api/v1/member/delete/{memberId}", memberId)
                                        .with(csrf())
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .characterEncoding(StandardCharsets.UTF_8))
                        .andDo(print());

        // then
        actions.andExpect(status().isOk());
        verify(memberDeleteUseCase, times(1)).deleteMember(anyLong());
    }
}
