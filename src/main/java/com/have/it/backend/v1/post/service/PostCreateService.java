package com.have.it.backend.v1.post.service;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.member.domain.Member;
import com.have.it.backend.v1.member.repository.MemberJpaRepository;
import com.have.it.backend.v1.post.domain.Post;
import com.have.it.backend.v1.post.dto.request.PostCreateRequest;
import com.have.it.backend.v1.post.repository.PostJpaRepository;
import com.have.it.backend.v1.post.service.usecase.PostCreateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostCreateService implements PostCreateUseCase {

    private final MemberJpaRepository memberJpaRepository;
    private final PostJpaRepository postJpaRepository;

    @Override
    public void registerPost(final PostCreateRequest request) {

        final Member findMember = memberJpaRepository
                .findById(request.memberId())
                .orElseThrow(() -> new BaseException(ExceptionInformation.ID_NO_CONTENT));

        final Post post = PostCreateRequest.createPost(findMember, request.title(), request.content());

        postJpaRepository.save(post);
    }
}
