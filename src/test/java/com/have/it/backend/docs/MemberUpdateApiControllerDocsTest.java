package com.have.it.backend.docs;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.epages.restdocs.apispec.ResourceSnippetParameters.builder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.Schema;
import com.have.it.backend.util.RestDocsTestSupport;
import com.have.it.backend.v1.member.dto.request.MemberUpdateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

class MemberUpdateApiControllerDocsTest extends RestDocsTestSupport {

    @Test
    @WithMockUser
    void 멤버_수정_API_문서화_테스트() throws Exception {

        // given
        Long memberId = 1L;

        MemberUpdateRequest request =
                MemberUpdateRequest.builder()
                        .nickname("test_member_nickname")
                        .password("test_member_password")
                        .build();

        willDoNothing().given(memberUpdateUseCase).updateMember(anyLong(), any());

        // when
        ResultActions actions =
                mockMvc
                        .perform(
                                patch("/api/v1/member/update/{memberId}", memberId)
                                        .with(csrf())
                                        .accept(MediaType.APPLICATION_JSON)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(request))
                                        .header(HttpHeaders.AUTHORIZATION, "test-header"))
                        .andDo(
                                document(
                                        "member-update",
                                        preprocessRequest(prettyPrint()),
                                        preprocessResponse(prettyPrint()),
                                        resource(
                                                builder()
                                                        .tag("멤버")
                                                        .description("멤버 수정")
                                                        .requestFields(
                                                                fieldWithPath("nickname")
                                                                        .type(JsonFieldType.STRING)
                                                                        .description("멤버 닉네임"),
                                                                fieldWithPath("password")
                                                                        .type(JsonFieldType.STRING)
                                                                        .description("멤버 비밀번호"))
                                                        .responseFields(
                                                                fieldWithPath("status")
                                                                        .type(JsonFieldType.NUMBER)
                                                                        .description("HTTP 응답 코드"),
                                                                fieldWithPath("data")
                                                                        .type(JsonFieldType.NUMBER)
                                                                        .description("기본 응답 데이터"))
                                                        .responseSchema(Schema.schema("MemberUpdateResponse"))
                                                        .build())));

        // then
        actions.andExpect(status().isOk());
    }
}
