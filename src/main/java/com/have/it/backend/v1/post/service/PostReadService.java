package com.have.it.backend.v1.post.service;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.post.domain.dto.response.PostReadResponse;
import com.have.it.backend.v1.post.domain.Post;
import com.have.it.backend.v1.post.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostReadService {

    private final PostJpaRepository repository;

    public PostReadResponse findPostById(Long id) {

        final Post post = repository
                .findById(id).orElseThrow(() -> new BaseException(ExceptionInformation.ID_NO_CONTENT));

        return PostReadResponse.from(post);
    }

    public List<PostReadResponse> findAll() {
        return repository
                .findAll()
                .stream()
                .map(PostReadResponse::from)
                .toList();
    }
}
