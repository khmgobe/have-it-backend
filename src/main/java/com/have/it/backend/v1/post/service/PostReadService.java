package com.have.it.backend.v1.post.service;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.post.domain.Post;
import com.have.it.backend.v1.post.domain.dto.response.PostReadResponse;
import com.have.it.backend.v1.post.service.port.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostReadService {

    private final PostRepository postRepository;

    public PostReadResponse findPostById(Long id) {

        final Post post = postRepository
                .findById(id).orElseThrow(
                        () -> new BaseException(ExceptionInformation.ID_NO_CONTENT));

        return PostReadResponse.from(post);
    }

    public List<PostReadResponse> findAllPostById() {
        return postRepository.findAll();
    }
}
