package com.have.it.backend.v1.post.service;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.post.domain.Post;
import com.have.it.backend.v1.post.dto.response.PostReadResponse;
import com.have.it.backend.v1.post.repository.PostJpaRepository;
import com.have.it.backend.v1.post.service.usecase.PostReadUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostReadService implements PostReadUseCase {

    private final PostJpaRepository repository;

    @Override
    public PostReadResponse findPostById(Long id) {

        final Post post =
                repository
                        .findById(id)
                        .orElseThrow(() -> new BaseException(ExceptionInformation.ID_NO_CONTENT));

        return PostReadResponse.toResponse(post);
    }

    @Override
    public List<PostReadResponse> findAll() {
        return repository.findAllWithWriter().stream().map(PostReadResponse::toResponse).toList();
    }
}
