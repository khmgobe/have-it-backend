package com.have.it.backend.v1.member.controller;

import com.have.it.backend.v1.member.domain.dto.response.MemberReadResponse;
import com.have.it.backend.v1.member.service.MemberReadService;
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
import java.util.List;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

@WebMvcTest(value = MemberReadApiController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemberReadApiControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private MemberReadService memberReadService;

        @Test
        @WithMockUser(value = "MEMBER")
        void 멤버를_한명_조회할_수_있다() throws Exception {

            // given
            Long memberId = 1L;
            MemberReadResponse response = MemberReadResponse.of(memberId, "test_response_nickname", "test_response_email");

            given(memberReadService.findMemberById(anyLong())).willReturn(response);

            // when
            ResultActions actions = mockMvc.perform(get("/api/v1/member/read/{memberId}", memberId)
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .characterEncoding(StandardCharsets.UTF_8))
                    .andDo(print());

            //then
            actions.andExpect(status().isOk())
                    .andExpect(jsonPath("$.data.id").value(memberId))
                    .andExpect(jsonPath("$.data.nickname").value(response.getNickname()))
                    .andExpect(jsonPath("$.data.email").value(response.getEmail()));
            verify(memberReadService, times(1)).findMemberById(anyLong());
        }

    @Test
    @WithMockUser(value = "MEMBER")
    void 멤버_목록을_조회할_수_있다() throws Exception {

        // given
        List<MemberReadResponse> response = List.of(
                MemberReadResponse.of(1L, "test_nickname", "test_email@naver.com"),
                MemberReadResponse.of(2L, "good_nickname", "good_email@naver.com"));

        given(memberReadService.findAllMember()).willReturn(response);

        // when
        ResultActions actions = mockMvc.perform(get("/api/v1/member/read")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print());

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1L))
                .andExpect(jsonPath("$.data[0].nickname").value("test_nickname"))
                .andExpect(jsonPath("$.data[0].email").value("test_email@naver.com"))
                .andExpect(jsonPath("$.data[1].id").value(2L))
                .andExpect(jsonPath("$.data[1].nickname").value("good_nickname"))
                .andExpect(jsonPath("$.data[1].email").value("good_email@naver.com"));

        verify(memberReadService, times(1)).findAllMember();
    }
}