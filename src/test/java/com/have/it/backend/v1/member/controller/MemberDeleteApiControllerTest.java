package com.have.it.backend.v1.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.have.it.backend.v1.member.service.MemberDeleteService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = MemberDeleteApiController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemberDeleteApiControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberDeleteService memberDeleteService;

    @Test
    @WithMockUser(value = "MEMBER")
    void 멤버를_삭제할_수_있다() throws Exception {

        // given
        Long memberId = 1L;

        willDoNothing().given(memberDeleteService).deleteMember(anyLong());

        // when
        ResultActions actions = mockMvc.perform(delete("/api/v1/member/delete/{memberId}", memberId)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print());

        //then
        actions.andExpect(status().isOk());
        verify(memberDeleteService, times(1)).deleteMember(anyLong());
    }
}