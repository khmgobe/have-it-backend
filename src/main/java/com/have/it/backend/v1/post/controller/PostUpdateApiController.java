package com.have.it.backend.v1.post.controller;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.post.dto.request.PostUpdateRequest;
import com.have.it.backend.v1.post.service.PostUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostUpdateApiController {


    private final PostUpdateService service;

    @PatchMapping("/api/v1/post/update/{postId}")
    public ResponseEntity<BaseResponse<Void>> updatePost(@PathVariable("postId") Long postId, @RequestBody PostUpdateRequest request) {

        service.update(postId, request.getTitle(), request.getContent());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success());

    }
}
