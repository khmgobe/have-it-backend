package com.have.it.backend.v1.post.service.usecase;

import com.have.it.backend.v1.post.dto.response.PostReadResponse;
import java.util.List;

public interface PostReadUseCase {
    PostReadResponse findPostById(Long postId);

    List<PostReadResponse> findAll();
}
