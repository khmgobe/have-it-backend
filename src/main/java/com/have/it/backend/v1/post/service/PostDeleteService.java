package com.have.it.backend.v1.post.service;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.post.repository.PostJpaRepository;
import com.have.it.backend.v1.post.service.usecase.PostDeleteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostDeleteService implements PostDeleteUseCase {

    private final PostJpaRepository repository;

    @Override
    public void deleteById(Long postId) {

        repository
                .findById(postId)
                .orElseThrow(() -> new BaseException(ExceptionInformation.ID_NO_CONTENT));

        repository.deleteById(postId);
    }
}
