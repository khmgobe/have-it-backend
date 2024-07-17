package com.have.it.backend.v1.post.service.usecase;

public interface PostCreateUseCase {

    void registerPost(Long memberId, String title, String content);
}
