package com.have.it.backend.v1.post.controller;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.post.domain.dto.request.PostCreateRequest;
import com.have.it.backend.v1.post.service.PostCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostCreateApiController {

    private final PostCreateService service;

    @PostMapping("/api/v1/post/register")
    public ResponseEntity<BaseResponse<Void>> write(@RequestBody PostCreateRequest request) {

        service.registerPost(request.getMemberId(), request.getTitle(), request.getContent());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BaseResponse.created());
    }
}
