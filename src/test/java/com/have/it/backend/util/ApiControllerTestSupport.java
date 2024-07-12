package com.have.it.backend.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.have.it.backend.v1.member.controller.MemberCreateApiController;
import com.have.it.backend.v1.member.controller.MemberDeleteApiController;
import com.have.it.backend.v1.member.controller.MemberReadApiController;
import com.have.it.backend.v1.member.controller.MemberUpdateApiController;
import com.have.it.backend.v1.member.service.MemberCreateService;
import com.have.it.backend.v1.member.service.MemberDeleteService;
import com.have.it.backend.v1.member.service.MemberReadService;
import com.have.it.backend.v1.member.service.MemberUpdateService;
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
    protected MemberCreateService memberCreateService;

    @MockBean
    protected MemberReadService memberReadService;

    @MockBean
    protected MemberUpdateService memberUpdateService;

    @MockBean
    protected MemberDeleteService memberDeleteService;


}
