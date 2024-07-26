package com.have.it.backend.docs;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.epages.restdocs.apispec.ResourceSnippetParameters.builder;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.Schema;
import com.have.it.backend.util.RestDocsTestSupport;
import com.have.it.backend.v1.post.dto.request.PostCreateRequest;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

class PostCreateApiControllerDocsTest extends RestDocsTestSupport {

    @Test
    @WithMockUser
    void 게시글_생성_API_문서화() throws Exception {

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
                mockMvc.perform(
                        post("/api/v1/post/register")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding(StandardCharsets.UTF_8)
                                .content(objectMapper.writeValueAsString(request)));

        // when
        actions.andDo(
                document(
                        "post-create",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                builder()
                                        .tag("게시")
                                        .description("게시글 등록")
                                        .requestFields(
                                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("멤버 아이디"),
                                                fieldWithPath("title").type(JsonFieldType.STRING).description("게시글 제목"),
                                                fieldWithPath("content").type(JsonFieldType.STRING).description("게시글 내용"))
                                        .responseFields(
                                                fieldWithPath("status")
                                                        .type(JsonFieldType.NUMBER)
                                                        .description("HTTP 응답 코드"),
                                                fieldWithPath("data")
                                                        .type(JsonFieldType.OBJECT)
                                                        .description("기본 응답 데이터")
                                                        .optional())
                                        .responseSchema(Schema.schema("PostCreateResponse"))
                                        .build())));

        // then
        actions.andExpect(status().isCreated());
    }
}
