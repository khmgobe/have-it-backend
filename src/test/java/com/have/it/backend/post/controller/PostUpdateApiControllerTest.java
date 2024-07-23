package com.have.it.backend.post.controller;

import com.have.it.backend.util.ApiControllerTestSupport;
import com.have.it.backend.v1.post.dto.request.PostUpdateRequest;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PostUpdateApiControllerTest extends ApiControllerTestSupport {


    @Test
    @WithMockUser(value = "MEMBER")
    void 게시글을_수정할_수_있다() throws Exception {

        // given
        Long postId = 1L;

        PostUpdateRequest request =
                PostUpdateRequest
                        .builder()
                        .title("test_title")
                        .content("test_content")
                        .build();

        willDoNothing().given(postUpdateUseCase).update(anyLong(), anyString(), anyString());

        // when
        ResultActions actions = mockMvc.perform(patch("/api/v1/post/update/{postId}", postId)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print());

        //then
        actions.andExpect(status().isOk());
        verify(postUpdateUseCase, times(1)).update(anyLong(), anyString(), anyString());
    }

    @Test
    @WithMockUser(value = "MEMBER")
    void 게시글을_수정할_때는_null_또는_빈_값이_아닌_정상적인_값이_있어야_한다() throws Exception {

        // given
        Long postId = 1L;

        PostUpdateRequest request =
                PostUpdateRequest
                        .builder()
                        .title(null)
                        .content(" ")
                        .build();

        willDoNothing().given(memberUpdateUseCase).updateMember(anyLong(), any());

        // when
        ResultActions actions = mockMvc.perform(patch("/api/v1/post/update/{postId}", postId)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(objectMapper.writeValueAsString(request)));

        actions.andDo(print())
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.data['title']").value("제목은 비어있을 수 없습니다."))
                .andExpect(jsonPath("$.data['content']").value("내용은 비어있을 수 없습니다."));

        //then
        actions.andExpect(status().isBadRequest());
        verify(postUpdateUseCase, times(0)).update(anyLong(), anyString(), anyString());
    }
}