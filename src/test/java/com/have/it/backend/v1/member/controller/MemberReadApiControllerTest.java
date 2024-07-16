package com.have.it.backend.v1.member.controller;

import com.have.it.backend.util.ApiControllerTestSupport;
import com.have.it.backend.v1.member.dto.response.MemberReadResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;
import java.nio.charset.StandardCharsets;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

class MemberReadApiControllerTest extends ApiControllerTestSupport {

        @Test
        @WithMockUser(value = "MEMBER")
        void 멤버를_한명_조회할_수_있다() throws Exception {

            // given
            Long memberId = 1L;

            MemberReadResponse response = MemberReadResponse.builder()
                    .id(memberId)
                    .nickname("test_response_nickname")
                    .email("test_response_email")
                    .build();

            given(memberReadUseCase.findMemberById(anyLong())).willReturn(response);

            // when
            ResultActions actions = mockMvc.perform(get("/api/v1/member/read/{memberId}", memberId)
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .characterEncoding(StandardCharsets.UTF_8))
                    .andDo(print());

            //then
            actions.andExpect(status().isOk())
                    .andExpect(jsonPath("$.data.id").value(memberId))
                    .andExpect(jsonPath("$.data.nickname").value(response.nickname()))
                    .andExpect(jsonPath("$.data.email").value(response.email()));
            verify(memberReadUseCase, times(1)).findMemberById(anyLong());
        }

    @Test
    @WithMockUser(value = "MEMBER")
    void 멤버_목록을_조회할_수_있다() throws Exception {

        // given
        List<MemberReadResponse> response = List.of(

                MemberReadResponse.builder()
                        .id(1L)
                        .nickname("test_response_nickname")
                        .email("test_response_email")
                        .build(),

                MemberReadResponse.builder()
                        .id(2L)
                        .nickname("good_nickname")
                        .email("good_email@naver.com")
                        .build());

        given(memberReadUseCase.findAllMember()).willReturn(response);

        // when
        ResultActions actions = mockMvc.perform(get("/api/v1/member/read")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print());

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1L))
                .andExpect(jsonPath("$.data[0].nickname").value("test_response_nickname"))
                .andExpect(jsonPath("$.data[0].email").value("test_response_email"))
                .andExpect(jsonPath("$.data[1].id").value(2L))
                .andExpect(jsonPath("$.data[1].nickname").value("good_nickname"))
                .andExpect(jsonPath("$.data[1].email").value("good_email@naver.com"));

        verify(memberReadUseCase, times(1)).findAllMember();
    }
}