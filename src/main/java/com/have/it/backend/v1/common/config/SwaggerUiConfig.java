package com.have.it.backend.v1.common.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerUiConfig {

    @Bean
    public GroupedOpenApi postApi() {
        return GroupedOpenApi.builder().group("post").pathsToMatch("/api/v1/post/**").build();
    }

    @Bean
    public GroupedOpenApi memberApi() {
        return GroupedOpenApi.builder().group("member").pathsToMatch("/api/v1/member/**").build();
    }

    @Bean
    public GroupedOpenApi folderApi() {
        return GroupedOpenApi.builder().group("folder").pathsToMatch("/api/v1/folder/**").build();
    }

    @Bean
    public GroupedOpenApi fileApi() {
        return GroupedOpenApi.builder().group("file").pathsToMatch("/api/v1/file/**").build();
    }
}
