package com.have.it.backend.v1.post.service.usecase;

public interface PostUpdateUseCase {

    void update(Long postId, String title, String content);
}
