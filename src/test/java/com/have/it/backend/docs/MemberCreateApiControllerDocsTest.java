package com.have.it.backend.docs;

import com.epages.restdocs.apispec.Schema;
import com.have.it.backend.util.RestDocsTestSupport;
import com.have.it.backend.v1.member.domain.enumeration.Role;
import com.have.it.backend.v1.member.dto.request.MemberCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;
import static com.epages.restdocs.apispec.ResourceSnippetParameters.builder;
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;

class MemberCreateApiControllerDocsTest extends RestDocsTestSupport {

    @Test
    @WithMockUser
    void 멤버_생성_API_문서화_테스트() throws Exception {

        // given

        MemberCreateRequest request = MemberCreateRequest.builder()
                        .email("test_email")
                        .nickname("test_nickname")
                        .password("test_password")
                        .role(Role.MEMBER)
                        .build();

        willDoNothing().given(memberCreateUseCase).registerMember(any());

        // when
        ResultActions actions = mockMvc.perform(post("/api/v1/member/register")
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "test-header")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andDo(document("member-create",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                builder()
                                        .tag("멤버")
                                        .description("멤버 생성")
                                        .requestFields(
                                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                                fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임"),
                                                fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                                                fieldWithPath("role").type(JsonFieldType.STRING).description("권한")
                                        )
                                        .responseFields(
                                                fieldWithPath("status").type(JsonFieldType.NUMBER).description("HTTP 응답 코드"),
                                                fieldWithPath("data").type(JsonFieldType.OBJECT).description("기본 응답 데이터").optional()
                                        )
                                        .responseSchema(Schema.schema("MemberCreateResponse"))
                                        .build()
                        )
                )
        );

        // then
        actions.andExpect(status().isCreated());
    }
}
