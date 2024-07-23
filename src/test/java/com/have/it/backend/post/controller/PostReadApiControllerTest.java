package com.have.it.backend.post.controller;

import com.have.it.backend.util.ApiControllerTestSupport;
import com.have.it.backend.v1.post.dto.response.PostReadResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;
import java.nio.charset.StandardCharsets;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PostReadApiControllerTest extends ApiControllerTestSupport {


    @Test
    @WithMockUser(value = "MEMBER")
    void 게시글을_한_건_조회할_수_있다() throws Exception {

        // given
        PostReadResponse response = PostReadResponse.builder()
                .id(1L)
                .memberId(1L)
                .title("test_title")
                .content("test_content")
                .build();

        given(postReadUseCase.findPostById(anyLong())).willReturn(response);

        // when
        ResultActions actions = mockMvc.perform(get("/api/v1/post/read/{postId}", 1L)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print());

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(response.getId()))
                .andExpect(jsonPath("$.data.title").value(response.getTitle()))
                .andExpect(jsonPath("$.data.content").value(response.getContent()));
        verify(postReadUseCase, times(1)).findPostById(anyLong());
    }

    @Test
    @WithMockUser(value = "MEMBER")
    void 게시글_목록을_조회할_수_있다() throws Exception {

        // given
        List<PostReadResponse> response = List.of(
                PostReadResponse.builder()
                        .id(1L)
                        .memberId(1L)
                        .title("test_title")
                        .content("test_content")
                        .build(),

                PostReadResponse.builder()
                        .id(2L)
                        .memberId(2L)
                        .title("test_title2")
                        .content("test_content2")
                        .build());

        given(postReadUseCase.findAll()).willReturn(response);

        // when
        ResultActions actions = mockMvc.perform(get("/api/v1/post/read")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print());

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1L))
                .andExpect(jsonPath("$.data[0].title").value("test_title"))
                .andExpect(jsonPath("$.data[0].content").value("test_content"))
                .andExpect(jsonPath("$.data[1].id").value(2L))
                .andExpect(jsonPath("$.data[1].title").value("test_title2"))
                .andExpect(jsonPath("$.data[1].content").value("test_content2"));

        verify(postReadUseCase, times(1)).findAll();
    }
}