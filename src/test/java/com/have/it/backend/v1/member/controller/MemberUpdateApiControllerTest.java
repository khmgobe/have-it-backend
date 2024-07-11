package com.have.it.backend.v1.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.have.it.backend.v1.member.domain.dto.request.MemberUpdateRequest;
import com.have.it.backend.v1.member.service.MemberUpdateService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = MemberUpdateApiController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemberUpdateApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberUpdateService memberUpdateService;

    @Test
    @WithMockUser(value = "MEMBER")
    void 멤버_닉네임을_수정할_수_있다() throws Exception {

        // given
        Long memberId = 1L;

        MemberUpdateRequest request =
                MemberUpdateRequest
                        .builder()
                        .nickname("test_member_nickname")
                        .build();

        willDoNothing().given(memberUpdateService).updateMember(anyLong(), anyString());

        // when
        ResultActions actions = mockMvc.perform(patch("/api/v1/member/update/{memberId}", memberId)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print());

        //then
        actions.andExpect(status().isOk());
        verify(memberUpdateService, times(1)).updateMember(anyLong(), anyString());
    }

    @Test
    @WithMockUser(value = "MEMBER")
    void 멤버를_수정할_때는_null_값이_아닌_정상적인_값이_있어야_한다() throws Exception {

        // given
        Long memberId = 1L;

        MemberUpdateRequest request =
                MemberUpdateRequest
                        .builder()
                        .nickname(null)
                        .build();

        willDoNothing().given(memberUpdateService).updateMember(anyLong(), anyString());

        // when
        ResultActions actions = mockMvc.perform(patch("/api/v1/member/update/{memberId}", memberId)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print());

        //then
        actions.andExpect(status().isBadRequest());
        verify(memberUpdateService, times(0)).updateMember(anyLong(), anyString());
    }

    @Test
    @WithMockUser(value = "MEMBER")
    void 멤버를_생성할_때는_비어있는_값이_아닌_정상적인_값이_있어야_한다() throws Exception {

        // given
        Long memberId = 1L;

        MemberUpdateRequest request =
                MemberUpdateRequest
                        .builder()
                        .nickname(" ")
                        .build();

        willDoNothing().given(memberUpdateService).updateMember(anyLong(), anyString());

        // when
        ResultActions actions = mockMvc.perform(patch("/api/v1/member/update/{memberId}", memberId)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print());

        //then
        actions.andExpect(status().isBadRequest());
        verify(memberUpdateService, times(0)).updateMember(anyLong(), anyString());
    }

}