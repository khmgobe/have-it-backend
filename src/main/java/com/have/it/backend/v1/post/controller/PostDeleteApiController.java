package com.have.it.backend.v1.post.controller;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.post.service.usecase.PostDeleteUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "POST-DELETE", description = "게시 삭제 관련 API ")
public class PostDeleteApiController {

    private final PostDeleteUseCase postDeleteUseCase;

    @DeleteMapping(path = "/api/v1/post/delete/{postId}")
    public ResponseEntity<BaseResponse<Long>> delete(@PathVariable("postId") Long postId) {

        postDeleteUseCase.deleteById(postId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(postId));
    }
}
