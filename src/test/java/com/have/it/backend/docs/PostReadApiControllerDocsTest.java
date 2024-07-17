package com.have.it.backend.docs;

import com.epages.restdocs.apispec.Schema;
import com.have.it.backend.util.RestDocsTestSupport;
import com.have.it.backend.v1.member.dto.response.MemberReadResponse;
import com.have.it.backend.v1.post.dto.response.PostReadResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.epages.restdocs.apispec.ResourceSnippetParameters.builder;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PostReadApiControllerDocsTest extends RestDocsTestSupport {

    @Test
    @WithMockUser
    void 게시글_한건_조회_API_문서화_테스트() throws Exception {

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
                .characterEncoding(StandardCharsets.UTF_8));

        actions.andDo(document("post-read",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                builder()
                                        .tag("게시")
                                        .description("게시글 한건 조회")
                                        .responseFields(
                                                fieldWithPath("status").type(JsonFieldType.NUMBER).description("HTTP 응답 코드"),
                                                fieldWithPath("data").type(JsonFieldType.OBJECT).description("기본 응답 데이터").optional(),
                                                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("게시글 아이디"),
                                                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("멤버 아이디"),
                                                fieldWithPath("data.title").type(JsonFieldType.STRING).description("게시글 제목"),
                                                fieldWithPath("data.content").type(JsonFieldType.STRING).description("게시글 내용")
                                        )
                                        .responseSchema(Schema.schema("PostReadResponse"))
                                        .build()
                        )
                )
        );

        // then
        actions.andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void 게시글_전체_조회_API_문서화_테스트() throws Exception {

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
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(response))
                .contentType(MediaType.APPLICATION_JSON));

            actions.andDo(document("post-readAll",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                builder()
                                        .tag("게시글")
                                        .description("게시글 전체 조회")
                                        .responseFields(
                                                fieldWithPath("status").type(JsonFieldType.NUMBER).description("HTTP 응답 코드"),
                                                fieldWithPath("data").type(JsonFieldType.ARRAY).description("기본 응답 데이터").optional(),
                                                fieldWithPath("data[0].id").type(JsonFieldType.NUMBER).description("게시글 아이디"),
                                                fieldWithPath("data[0].memberId").type(JsonFieldType.NUMBER).description("멤버 아이디"),
                                                fieldWithPath("data[0].title").type(JsonFieldType.STRING).description("게시글 제목"),
                                                fieldWithPath("data[0].content").type(JsonFieldType.STRING).description("게시글 내용"),
                                                fieldWithPath("data[1].id").type(JsonFieldType.NUMBER).description("게시글 아이디"),
                                                fieldWithPath("data[1].memberId").type(JsonFieldType.NUMBER).description("멤버 아이디"),
                                                fieldWithPath("data[1].title").type(JsonFieldType.STRING).description("게시글 제목"),
                                                fieldWithPath("data[1].content").type(JsonFieldType.STRING).description("게시글 내용")
                                        )
                                        .responseSchema(Schema.schema("PostReadResponse"))
                                        .build()
                        )
                )
        );

        // then
        actions.andExpect(status().isOk());
    }
}
