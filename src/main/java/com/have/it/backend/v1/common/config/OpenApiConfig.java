package com.have.it.backend.v1.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class OpenApiConfig {

    private static final String API_TITLE = "Have-it 백엔드 서버 문서";
    private static final String API_DESCRIPTION = "";
    private static final String SERVER_URL = "/";
    private static final String SERVER_DESCRIPTION = "현재 위치한 서버";
    private static final String SECURITY_SCHEME_NAME = "bearerAuth";
    private static final String SECURITY_SCHEME_TYPE = "bearer";

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version:}") String appVersion) {
        io.swagger.v3.oas.models.servers.Server currentServer =
                new io.swagger.v3.oas.models.servers.Server();
        currentServer.setUrl(SERVER_URL);
        currentServer.setDescription(SERVER_DESCRIPTION);

        return new OpenAPI()
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        SECURITY_SCHEME_NAME,
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme(SECURITY_SCHEME_TYPE)
                                                .in(SecurityScheme.In.HEADER)
                                                .name(HttpHeaders.AUTHORIZATION)))
                .servers(Collections.singletonList(currentServer))
                .info(new Info().title(API_TITLE).version(appVersion).description(API_DESCRIPTION));
    }
}
