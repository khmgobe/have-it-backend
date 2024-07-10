package com.have.it.backend.v1.post.controller;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.post.service.PostDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostDeleteApiController {

    private final PostDeleteService service;

    @DeleteMapping("/api/v1/post/delete/{postId}")
    public ResponseEntity<BaseResponse<Long>> delete(@PathVariable("postId") Long postId) {

        service.deleteById(postId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(postId));
    }
}
