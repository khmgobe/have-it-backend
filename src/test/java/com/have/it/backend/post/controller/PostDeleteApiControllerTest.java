package com.have.it.backend.post.controller;

import com.have.it.backend.util.ApiControllerTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
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

class PostDeleteApiControllerTest extends ApiControllerTestSupport {


    @Test
    @WithMockUser(value = "MEMBER")
    void 게시글을_삭제한다() throws Exception {

        // given
        Long postId = 1L;

        // when
        willDoNothing().given(postDeleteUseCase).deleteById(anyLong());

        ResultActions actions = mockMvc.perform(delete("/api/v1/post/delete/{postId}", postId)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                        .andDo(print());

        //then
        actions.andExpect(status().isOk());
        verify(postDeleteUseCase, times(1)).deleteById(anyLong());
    }
}