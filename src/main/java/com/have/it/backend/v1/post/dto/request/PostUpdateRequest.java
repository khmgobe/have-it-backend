package com.have.it.backend.v1.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record PostUpdateRequest(
        @NotBlank(message = "제목은 비어있을 수 없습니다.") String title,
        @NotBlank(message = "내용은 비어있을 수 없습니다.") String content) {}
