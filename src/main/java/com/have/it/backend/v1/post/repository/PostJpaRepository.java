package com.have.it.backend.v1.post.repository;

import com.have.it.backend.v1.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostJpaRepository extends JpaRepository<Post, Long> {

        @Query("SELECT p FROM Post p JOIN FETCH p.writer w")
        List<Post> findAllWithWriter();

        @Query("SELECT p FROM Post p JOIN FETCH p.writer WHERE p.id = :postId")
        Optional<Post> findByIdWithWriter(@Param("postId") Long postId);
    }
