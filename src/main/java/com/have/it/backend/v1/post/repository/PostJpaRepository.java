package com.have.it.backend.v1.post.repository;

import com.have.it.backend.v1.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
}
