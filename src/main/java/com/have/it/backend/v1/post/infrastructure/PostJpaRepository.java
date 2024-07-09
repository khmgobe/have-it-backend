package com.have.it.backend.v1.post.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {
}
