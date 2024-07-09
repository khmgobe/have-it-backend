package com.have.it.backend.v1.post.service.port;

import com.have.it.backend.v1.post.domain.Post;
import com.have.it.backend.v1.post.domain.dto.response.PostReadResponse;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);

    Optional<Post> findById(Long id);

    List<PostReadResponse> findAll();
}
