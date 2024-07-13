package com.have.it.backend.v1.post.service;

import com.have.it.backend.v1.member.dto.response.MemberReadResponse;
import com.have.it.backend.v1.member.service.MemberReadService;
import com.have.it.backend.v1.post.domain.Post;
import com.have.it.backend.v1.post.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostCreateService {

    private final MemberReadService memberReadService;
    private final PostJpaRepository repository;

    public void registerPost(Long memberId, String title, String content) {

        final MemberReadResponse member = memberReadService.findMemberById(memberId);

        final Post post = Post.of(title, content, member.toModel());

        repository.save(post);
    }
}
