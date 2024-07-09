package com.have.it.backend.v1.post.controller;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.post.domain.dto.request.PostUpdateRequest;
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


    private final PostUpdateService postUpdateService;

    @PatchMapping("/api/v1/post/{postId}")
    public ResponseEntity<BaseResponse<Void>> updatePost(@PathVariable("postId") Long postId, @RequestBody PostUpdateRequest postUpdateRequest) {

        postUpdateService.update(postId, postUpdateRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success());

    }
}
