package com.have.it.backend.v1.member.controller;

import com.have.it.backend.util.ApiControllerTestSupport;
import com.have.it.backend.v1.member.domain.enumeration.Role;
import com.have.it.backend.v1.member.dto.request.MemberCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;
import java.nio.charset.StandardCharsets;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberCreateApiControllerTest extends ApiControllerTestSupport {

    @Test
    @WithMockUser(value = "MEMBER")
    void 멤버를_생성할_수_있다() throws Exception {

        // given
        MemberCreateRequest request =
                MemberCreateRequest
                        .builder()
                        .email("test@gmail.com")
                        .nickname("testNickname")
                        .password("testPassword")
                        .role(Role.MEMBER)
                        .build();

        willDoNothing().given(memberCreateUseCase).registerMember(any());

        // when
        ResultActions actions = mockMvc.perform(post("/api/v1/member/register")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print());

        //then
        actions.andExpect(status().isCreated());
        verify(memberCreateUseCase, times(1)).registerMember(any());
    }

    @Test
    @WithMockUser(value = "MEMBER")
    void 멤버를_생성할_때는_null_값이_아닌_정상적인_값이_있어야_한다() throws Exception {

        // given
        MemberCreateRequest request =
                MemberCreateRequest
                        .builder()
                        .email(null)
                        .nickname(null)
                        .password(null)
                        .role(null)
                        .build();

        willDoNothing().given(memberCreateUseCase).registerMember(any());

        // when
        ResultActions actions = mockMvc.perform(post("/api/v1/member/register")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(objectMapper.writeValueAsString(request)));

        actions.andDo(print())
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.data['email']").value("이메일은 비어있을 수 없습니다."))
                .andExpect(jsonPath("$.data['nickname']").value("닉네임은 비어있을 수 없습니다."))
                .andExpect(jsonPath("$.data['password']").value("비밀번호는 비어있을 수 없습니다."));

        //then
        actions.andExpect(status().isBadRequest());
        verify(memberCreateUseCase, times(0)).registerMember(any());
    }

    @Test
    @WithMockUser(value = "MEMBER")
    void 멤버를_생성할_때는_비어있는_값이_아닌_정상적인_값이_있어야_한다() throws Exception {

        // given
        MemberCreateRequest request =
                MemberCreateRequest
                        .builder()
                        .email(" ")
                        .nickname(" ")
                        .password(" ")
                        .build();

        willDoNothing().given(memberCreateUseCase).registerMember(any());

        // when
        ResultActions actions = mockMvc.perform(post("/api/v1/member/register")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(objectMapper.writeValueAsString(request)));

        actions.andDo(print())
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.data['email']").value("이메일은 비어있을 수 없습니다."))
                .andExpect(jsonPath("$.data['nickname']").value("닉네임은 비어있을 수 없습니다."))
                .andExpect(jsonPath("$.data['password']").value("비밀번호는 비어있을 수 없습니다."));

        //then
        actions.andExpect(status().isBadRequest());
        verify(memberCreateUseCase, times(0)).registerMember(any());
    }
}