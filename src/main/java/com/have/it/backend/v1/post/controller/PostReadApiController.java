package com.have.it.backend.v1.post.controller;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.post.dto.response.PostReadResponse;
import com.have.it.backend.v1.post.service.usecase.PostReadUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "POST-READ", description = "게시 조회 관련 API ")
public class PostReadApiController {

    private final PostReadUseCase postReadUseCase;

    @GetMapping(path = "/api/v1/post/read/{postId}")
    public ResponseEntity<BaseResponse<PostReadResponse>> readPost(
            @PathVariable("postId") Long postId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.success(postReadUseCase.findPostById(postId)));
    }

    @GetMapping("/api/v1/post/read")
    public ResponseEntity<BaseResponse<List<PostReadResponse>>> readAllPost() {

        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.success(postReadUseCase.findAll()));
    }
}
