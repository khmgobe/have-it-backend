package com.have.it.backend.v1.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.have.it.backend.v1.member.service.MemberCreateService;
import com.have.it.backend.v1.member.domain.dto.request.MemberCreateRequest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.nio.charset.StandardCharsets;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = MemberCreateApiController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemberCreateApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberCreateService memberCreateService;

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
                        .build();

        willDoNothing().given(memberCreateService).registerMember(anyString(), anyString(), anyString());

        // when
        ResultActions actions = mockMvc.perform(post("/api/v1/member/register")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(objectMapper.writeValueAsString(request)))
                        .andDo(print());

        //then
        actions.andExpect(status().isCreated());
        verify(memberCreateService, times(1)).registerMember(anyString(), anyString(), anyString());
    }

    @Test
    @WithMockUser(value = "MEMBER")
    void 멤버를_생성할_때는_null_값이_아닌_정상적인_값이_있어야_한다() throws Exception {

        // given
        MemberCreateRequest request =
                MemberCreateRequest
                        .builder()
                        .email(null)
                        .nickname(" ")
                        .password(" ")
                        .build();

        willDoNothing().given(memberCreateService).registerMember(anyString(), anyString(), anyString());

        // when
        ResultActions actions = mockMvc.perform(post("/api/v1/member/register")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print());

        //then
        actions.andExpect(status().isBadRequest());
        verify(memberCreateService, times(0)).registerMember(anyString(), anyString(), anyString());
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

        willDoNothing().given(memberCreateService).registerMember(anyString(), anyString(), anyString());

        // when
        ResultActions actions = mockMvc.perform(post("/api/v1/member/register")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print());

        //then
        actions.andExpect(status().isBadRequest());
        verify(memberCreateService, times(0)).registerMember(anyString(), anyString(), anyString());
    }
}