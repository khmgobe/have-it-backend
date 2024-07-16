package com.have.it.backend.docs;

import com.epages.restdocs.apispec.Schema;
import com.have.it.backend.util.RestDocsTestSupport;
import com.have.it.backend.v1.member.dto.response.MemberReadResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.List;
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.epages.restdocs.apispec.ResourceSnippetParameters.builder;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;

class MemberReadApiControllerDocsTest extends RestDocsTestSupport {

    @Test
    @WithMockUser
    void 멤버_한건_조회_API_문서화_테스트() throws Exception {

        // given
        Long memberId = 1L;

        MemberReadResponse response = MemberReadResponse.builder()
                .id(memberId)
                .nickname("test_response_nickname")
                .email("test_response_email")
                .build();

        given(memberReadUseCase.findMemberById(anyLong())).willReturn(response);

        // when
        ResultActions actions = mockMvc.perform(get("/api/v1/member/read/{memberId}", memberId)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "test-header")
        ).andDo(document("member-read",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                builder()
                                        .tag("멤버")
                                        .description("멤버 조회")
                                        .responseFields(
                                                fieldWithPath("status").type(JsonFieldType.NUMBER).description("HTTP 응답 코드"),
                                                fieldWithPath("data").type(JsonFieldType.OBJECT).description("기본 응답 데이터").optional(),
                                                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("멤버 아이디"),
                                                fieldWithPath("data.nickname").type(JsonFieldType.STRING).description("멤버 닉네임"),
                                                fieldWithPath("data.email").type(JsonFieldType.STRING).description("멤버 이메일")
                                        )
                                        .responseSchema(Schema.schema("MemberReadResponse"))
                                        .build()
                        )
                )
        );

        // then
        actions.andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void 멤버_전체_조회_API_문서화_테스트() throws Exception {

        // given

        List<MemberReadResponse> response = List.of(

                MemberReadResponse.builder()
                        .id(1L)
                        .nickname("test_response_nickname")
                        .email("test_response_email")
                        .build(),

                MemberReadResponse.builder()
                        .id(2L)
                        .nickname("good_nickname")
                        .email("good_email@naver.com")
                        .build());


        given(memberReadUseCase.findAllMember()).willReturn(response);

        // when
        ResultActions actions = mockMvc.perform(get("/api/v1/member/read")
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "test-header")
        ).andDo(document("member-readAll",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                builder()
                                        .tag("멤버")
                                        .description("멤버 전체 조회")
                                        .responseFields(
                                                fieldWithPath("status").type(JsonFieldType.NUMBER).description("HTTP 응답 코드"),
                                                fieldWithPath("data").type(JsonFieldType.ARRAY).description("기본 응답 데이터").optional(),
                                                fieldWithPath("data[0].id").type(JsonFieldType.NUMBER).description("멤버 아이디"),
                                                fieldWithPath("data[0].nickname").type(JsonFieldType.STRING).description("멤버 닉네임"),
                                                fieldWithPath("data[0].email").type(JsonFieldType.STRING).description("멤버 이메일"),
                                                fieldWithPath("data[1].id").type(JsonFieldType.NUMBER).description("멤버 아이디"),
                                                fieldWithPath("data[1].nickname").type(JsonFieldType.STRING).description("멤버 닉네임"),
                                                fieldWithPath("data[1].email").type(JsonFieldType.STRING).description("멤버 이메일")
                                        )
                                        .responseSchema(Schema.schema("MemberReadResponse"))
                                        .build()
                        )
                )
        );

        // then
        actions.andExpect(status().isOk());
    }
}
