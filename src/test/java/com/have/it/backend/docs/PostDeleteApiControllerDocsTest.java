package com.have.it.backend.docs;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.epages.restdocs.apispec.ResourceSnippetParameters.builder;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.Schema;
import com.have.it.backend.util.RestDocsTestSupport;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

class PostDeleteApiControllerDocsTest extends RestDocsTestSupport {

    @Test
    @WithMockUser
    void 게시글_삭제_API_문서화_테스트() throws Exception {

        // given
        Long postId = 1L;

        // when
        willDoNothing().given(postDeleteUseCase).deleteById(anyLong());

        ResultActions actions =
                mockMvc.perform(
                        delete("/api/v1/post/delete/{postId}", postId)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding(StandardCharsets.UTF_8));

        actions.andDo(
                document(
                        "post-delete",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                builder()
                                        .tag("게시")
                                        .description("게시글 삭제")
                                        .responseFields(
                                                fieldWithPath("status")
                                                        .type(JsonFieldType.NUMBER)
                                                        .description("HTTP 응답 코드"),
                                                fieldWithPath("data")
                                                        .type(JsonFieldType.NUMBER)
                                                        .description("기본 응답 데이터")
                                                        .optional())
                                        .responseSchema(Schema.schema("PostDeleteResponse"))
                                        .build())));

        // then
        actions.andExpect(status().isOk());
    }
}
