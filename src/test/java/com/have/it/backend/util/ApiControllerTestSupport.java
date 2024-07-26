package com.have.it.backend.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.have.it.backend.v1.member.controller.MemberCreateApiController;
import com.have.it.backend.v1.member.controller.MemberDeleteApiController;
import com.have.it.backend.v1.member.controller.MemberReadApiController;
import com.have.it.backend.v1.member.controller.MemberUpdateApiController;
import com.have.it.backend.v1.member.service.usecase.MemberCreateUseCase;
import com.have.it.backend.v1.member.service.usecase.MemberDeleteUseCase;
import com.have.it.backend.v1.member.service.usecase.MemberReadUseCase;
import com.have.it.backend.v1.member.service.usecase.MemberUpdateUseCase;
import com.have.it.backend.v1.post.controller.PostCreateApiController;
import com.have.it.backend.v1.post.controller.PostDeleteApiController;
import com.have.it.backend.v1.post.controller.PostReadApiController;
import com.have.it.backend.v1.post.controller.PostUpdateApiController;
import com.have.it.backend.v1.post.service.usecase.PostCreateUseCase;
import com.have.it.backend.v1.post.service.usecase.PostDeleteUseCase;
import com.have.it.backend.v1.post.service.usecase.PostReadUseCase;
import com.have.it.backend.v1.post.service.usecase.PostUpdateUseCase;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(
        controllers = {
            MemberCreateApiController.class,
            MemberReadApiController.class,
            MemberUpdateApiController.class,
            MemberDeleteApiController.class,
            PostCreateApiController.class,
            PostReadApiController.class,
            PostUpdateApiController.class,
            PostDeleteApiController.class
        })
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public abstract class ApiControllerTestSupport {

    @Autowired protected MockMvc mockMvc;

    @Autowired protected ObjectMapper objectMapper;

    @MockBean protected MemberCreateUseCase memberCreateUseCase;

    @MockBean protected MemberReadUseCase memberReadUseCase;

    @MockBean protected MemberUpdateUseCase memberUpdateUseCase;

    @MockBean protected MemberDeleteUseCase memberDeleteUseCase;

    @MockBean protected PostCreateUseCase postCreateUseCase;

    @MockBean protected PostReadUseCase postReadUseCase;

    @MockBean protected PostUpdateUseCase postUpdateUseCase;

    @MockBean protected PostDeleteUseCase postDeleteUseCase;
}
