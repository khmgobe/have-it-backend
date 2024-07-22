package com.have.it.backend.v1.post.controller;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.post.dto.request.PostCreateRequest;
import com.have.it.backend.v1.post.service.usecase.PostCreateUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "POST-CREATE", description = "게시 생성 관련 API ")
public class PostCreateApiController {

    private final PostCreateUseCase postCreateUseCase;

    @PostMapping(path = "/api/v1/post/register")
    public ResponseEntity<BaseResponse<Void>> write(@Valid @RequestBody PostCreateRequest request) {

        postCreateUseCase.registerPost(request.memberId(), request.title(), request.content());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BaseResponse.created());
    }
}
