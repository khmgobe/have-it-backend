package com.have.it.backend.v1.post.controller;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.post.dto.response.PostReadResponse;
import com.have.it.backend.v1.post.service.usecase.PostReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostReadApiController {

    private final PostReadUseCase postReadUseCase;

    @GetMapping(path = "/api/v1/post/read/{postId}")
    public ResponseEntity<BaseResponse<PostReadResponse>> readPost(@PathVariable("postId") Long postId) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(postReadUseCase.findPostById(postId)));
    }

    @GetMapping("/api/v1/post/read")
    public ResponseEntity<BaseResponse<List<PostReadResponse>>> readAllPost() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(postReadUseCase.findAll()));
    }
}
