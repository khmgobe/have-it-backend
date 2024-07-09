package com.have.it.backend.v1.post.infrastructure;

import com.have.it.backend.v1.post.domain.Post;
import com.have.it.backend.v1.post.domain.dto.response.PostReadResponse;
import com.have.it.backend.v1.post.service.port.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final PostJpaRepository jpaRepository;

    @Override
    public Post save(Post post) {
        return jpaRepository.save(PostEntity.fromModel(post)).toModel();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return jpaRepository.findById(id).map(PostEntity::toModel);
    }

    @Override
    public List<PostReadResponse> findAll() {
        return jpaRepository
                .findAll()
                .stream()
                .map(PostEntity::toResponse).toList();
    }
}
