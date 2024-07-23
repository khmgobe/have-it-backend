package com.have.it.backend.v1.post.service.usecase;

import com.have.it.backend.v1.post.dto.request.PostCreateRequest;

public interface PostCreateUseCase {

    void registerPost(PostCreateRequest serviceRequest);
}
