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
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
        MemberCreateApiController.class,
        MemberReadApiController.class,
        MemberUpdateApiController.class,
        MemberDeleteApiController.class
})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public abstract class ApiControllerTestSupport {


    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected MemberCreateUseCase memberCreateUseCase;

    @MockBean
    protected MemberReadUseCase memberReadUseCase;

    @MockBean
    protected MemberUpdateUseCase memberUpdateUseCase;

    @MockBean
    protected MemberDeleteUseCase memberDeleteUseCase;
}
