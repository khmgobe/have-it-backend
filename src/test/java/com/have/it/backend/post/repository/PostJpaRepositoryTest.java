package com.have.it.backend.post.repository;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.member.domain.Member;
import com.have.it.backend.v1.member.domain.enumeration.Role;
import com.have.it.backend.v1.member.repository.MemberJpaRepository;
import com.have.it.backend.v1.post.domain.Post;
import com.have.it.backend.v1.post.repository.PostJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PostJpaRepositoryTest {

    @Autowired
    PostJpaRepository postJpaRepository;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @BeforeEach
    void setUp() {
        postJpaRepository.deleteAllInBatch();
        memberJpaRepository.deleteAllInBatch();
    }

    private Member createAndSaveMember(final String email, final String nickname, final String password, final Role role) {
        Member member = Member.fromCreate(email, nickname, password, role);
        return memberJpaRepository.save(member);
    }

    private Post createPost(final String title, final String content, final Member writer) {
        return Post.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();
    }

    @Test
    void 게시글을_삭제한다() {

        // given
        final Member findMember = createAndSaveMember("test@naver.com", "test_nickname", "test_password", Role.MEMBER);

        final Post post = createPost("test_title", "test_content", findMember);

        postJpaRepository.save(post);

        // when
        final Post findPost = postJpaRepository
                .findById(post.getId())
                .orElseThrow(() -> new BaseException(ExceptionInformation.ID_NO_CONTENT));
        postJpaRepository.deleteById(findPost.getId());

        // then
        assertThat(postJpaRepository.findById(findPost.getId())).isEmpty();
    }

    @Test
    void 게시글을_정상적으로_저장할_수_있다() {

        // given
        final Member findMember = createAndSaveMember("test@naver.com", "test_nickname", "test_password", Role.MEMBER);

        final Post post = createPost("test_title", "test_content", findMember);

        // when
        final Post savedPost = postJpaRepository.save(post);

        final Post findPost = postJpaRepository
                .findById(savedPost.getId())
                .orElseThrow(() -> new BaseException(ExceptionInformation.ID_NO_CONTENT));

        // then
        assertThat(savedPost)
                .extracting("id", "title", "content")
                .containsExactly(findPost.getId(), findPost.getTitle(), findPost.getContent());
    }

    @Test
    void 게시글을_작성하고_작성자와_작성자의_아이디를_확인할_수_있어야_한다() {

        // given
        final Member findMember = createAndSaveMember("test@naver.com", "test_nickname", "test_password", Role.MEMBER);

        final Post post = createPost("test_title", "test_content", findMember);

        // when
        final Post savedPost = postJpaRepository.save(post);

        final Post findPost = postJpaRepository
                .findByIdWithWriter(savedPost.getId())
                .orElseThrow(() -> new BaseException(ExceptionInformation.ID_NO_CONTENT));

        // then
        assertThat(savedPost)
                .extracting("id", "title", "content")
                .containsExactly(findPost.getId(), findPost.getTitle(), findPost.getContent());

        assertThat(findPost.getWriter())
                .extracting("id", "nickname")
                .containsExactly(findMember.getId(), findMember.getNickname());
    }

    @Test
    void 저장한_게시글_목록을_정상적으로_조회할_수_있어야한다() {

        // given
        final Member findMember = createAndSaveMember("test@naver.com", "test_nickname", "test_password", Role.MEMBER);

        final List<Post> postList = List.of(
                createPost("test_title", "test_content", findMember),
                createPost("test_title2", "test_content2", findMember)
        );

        // when
        postJpaRepository.saveAll(postList);
        final List<Post> findAllPost = postJpaRepository.findAll();

        // then
        assertThat(findAllPost).hasSize(2)
                .extracting("title", "content", "writer")
                .containsExactlyInAnyOrder(
                        tuple(postList.get(0).getTitle(), postList.get(0).getContent(), postList.get(0).getWriter()),
                        tuple(postList.get(1).getTitle(), postList.get(1).getContent(), postList.get(1).getWriter()));
    }

    @Test
    void 저장한_게시글_목록을_정상적으로_조회할_수_있어야하며_작성자의_닉네임도_함께_확인할_수_있어야_한다() {
        // given
        final Member findMember = createAndSaveMember("test@naver.com", "test_nickname", "test_password", Role.MEMBER);
        final Member findMember2 = createAndSaveMember("test@naver.com", "test_nickname2", "test_password", Role.ADMIN);

        final List<Post> postList = List.of(
                createPost("test_title", "test_content", findMember),
                createPost("test_title2", "test_content2", findMember2)
        );

        // when
        postJpaRepository.saveAll(postList);
        final List<Post> findAllPost = postJpaRepository.findAllWithWriter();

        // then
        assertThat(findAllPost).hasSize(2)
                .extracting(post -> post.getWriter().getNickname())
                .containsExactlyInAnyOrder("test_nickname", "test_nickname2");
    }
}
