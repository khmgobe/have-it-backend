package com.have.it.backend.docs;

import com.epages.restdocs.apispec.Schema;
import com.have.it.backend.util.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.epages.restdocs.apispec.ResourceSnippetParameters.builder;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;

class MemberDeleteApiControllerDocsTest extends RestDocsTestSupport {

    @Test
    @WithMockUser
    void 멤버_삭제_API_문서화_테스트() throws Exception {

        // given
        Long memberId = 1L;

        willDoNothing().given(memberDeleteUseCase).deleteMember(anyLong());

        // when
        ResultActions actions = mockMvc.perform(delete("/api/v1/member/delete/{memberId}", memberId)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "test-header")
        ).andDo(document("member-delete",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                builder()
                                        .tag("멤버")
                                        .description("멤버 삭제")
                                        .responseFields(
                                                fieldWithPath("status").type(JsonFieldType.NUMBER).description("HTTP 응답 코드"),
                                                fieldWithPath("data").type(JsonFieldType.NUMBER).description("기본 응답 데이터").optional()
                                        )
                                        .responseSchema(Schema.schema("MemberDeleteResponse"))
                                        .build()
                        )
                )
        );

        // then
        actions.andExpect(status().isOk());
    }
}
