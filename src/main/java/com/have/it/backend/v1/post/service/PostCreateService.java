package com.have.it.backend.v1.post.service;

import com.have.it.backend.v1.member.domain.dto.response.MemberReadResponse;
import com.have.it.backend.v1.member.service.MemberReadService;
import com.have.it.backend.v1.post.domain.Post;
import com.have.it.backend.v1.post.domain.dto.request.PostCreateRequest;
import com.have.it.backend.v1.post.service.port.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostCreateService {

    private final PostRepository postRepository;
    private final MemberReadService memberReadService;

    public void registerPost(PostCreateRequest postCreateRequest) {

        MemberReadResponse member = memberReadService.findMemberById(postCreateRequest.getMemberId());

        Post post = Post.of(postCreateRequest, member.toModel());

        postRepository.save(post);
    }
}