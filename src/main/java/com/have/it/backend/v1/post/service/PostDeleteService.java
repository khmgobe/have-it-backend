package com.have.it.backend.v1.post.service;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.post.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostDeleteService {

    private final PostJpaRepository repository;

    public void deleteById(Long postId) {

       validatePostId(postId);
       repository.deleteById(postId);
    }

    private void validatePostId(Long postId) {

        repository
                .findById(postId)
                .orElseThrow(() -> new BaseException(ExceptionInformation.ID_NO_CONTENT));
    }
}