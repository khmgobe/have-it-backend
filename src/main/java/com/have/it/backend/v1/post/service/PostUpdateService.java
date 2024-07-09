package com.have.it.backend.v1.post.service;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.post.domain.Post;
import com.have.it.backend.v1.post.domain.dto.request.PostUpdateRequest;
import com.have.it.backend.v1.post.infrastructure.PostEntity;
import com.have.it.backend.v1.post.service.port.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostUpdateService {

    private final PostRepository postRepository;

    public void update (Long postId, PostUpdateRequest postUpdateRequest) {

        Post post = postRepository
                        .findById(postId)
                        .orElseThrow(
                                () -> new BaseException(ExceptionInformation.ID_NO_CONTENT));

        post.update(postUpdateRequest.getTitle(), postUpdateRequest.getContent());

    }
}
