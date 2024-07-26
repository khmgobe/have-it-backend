package com.have.it.backend.post.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.have.it.backend.util.ApiControllerTestSupport;
import com.have.it.backend.v1.post.dto.request.PostCreateRequest;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

class PostCreateApiControllerTest extends ApiControllerTestSupport {

    @Test
    @WithMockUser(value = "MEMBER")
    void 게시글을_등록할_수_있다() throws Exception {

        // given
        PostCreateRequest request =
                PostCreateRequest.builder()
                        .memberId(1L)
                        .title("test_title")
                        .content("test_content")
                        .build();

        willDoNothing().given(postCreateUseCase).registerPost(any());

        // when
        ResultActions actions =
                mockMvc
                        .perform(
                                post("/api/v1/post/register")
                                        .with(csrf())
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .characterEncoding(StandardCharsets.UTF_8)
                                        .content(objectMapper.writeValueAsString(request)))
                        .andDo(print());

        // then
        actions.andExpect(status().isCreated());
        verify(postCreateUseCase, times(1)).registerPost(any());
    }

    @Test
    @WithMockUser(value = "MEMBER")
    void 게시글을_등록할_때는_멤버아이디가_1_이상이어야_한다() throws Exception {

        // given
        PostCreateRequest request =
                PostCreateRequest.builder()
                        .memberId(0L)
                        .title("test_title")
                        .content("test_content")
                        .build();

        willDoNothing().given(postCreateUseCase).registerPost(any());

        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/api/v1/post/register")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding(StandardCharsets.UTF_8)
                                .content(objectMapper.writeValueAsString(request)));

        actions
                .andDo(print())
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.data['memberId']").value("멤버 아이디는 1 이상 이어야 합니다."));

        // then
        actions.andExpect(status().isBadRequest());
        verify(postCreateUseCase, times(0)).registerPost(any());
    }

    @Test
    @WithMockUser(value = "MEMBER")
    void 게시글을_등록할_때는_멤버아이디가_1_이상이자_제목_및_내용이_비어있거나_null값이지_않아야_한다() throws Exception {

        // given
        PostCreateRequest request =
                PostCreateRequest.builder().memberId(0L).title(" ").content(null).build();

        willDoNothing().given(postCreateUseCase).registerPost(any());

        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/api/v1/post/register")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding(StandardCharsets.UTF_8)
                                .content(objectMapper.writeValueAsString(request)));

        actions
                .andDo(print())
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.data['memberId']").value("멤버 아이디는 1 이상 이어야 합니다."))
                .andExpect(jsonPath("$.data['title']").value("제목은 비어있을 수 없습니다."))
                .andExpect(jsonPath("$.data['content']").value("내용은 비어있을 수 없습니다."));

        // then
        actions.andExpect(status().isBadRequest());
        verify(postCreateUseCase, times(0)).registerPost(any());
    }
}
