package com.choibokeun.myproject.service;

import com.choibokeun.myproject.domain.Member;
import com.choibokeun.myproject.dto.AddMemberRequest;
import com.choibokeun.myproject.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void clean() {
        memberRepository.deleteAll();
    }

    @DisplayName("save(): 회원 정보가 저장된다")
    @Test
    void save() {
        // given
        final String name = "testName";
        final String content = "content";
        final AddMemberRequest request = new AddMemberRequest(name, content);

        // when
        Member savedMember = memberService.save(request);

        // then
        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(1);
        assertThat(members.get(0).getName()).isEqualTo(name);
        assertThat(members.get(0).getContent()).isEqualTo(content);
    }

    @DisplayName("findAll(): 모든 회원 정보를 조회한다")
    @Test
    void findAll() {
        // given
        final String name = "testName";
        final String content = "content";
        memberRepository.save(Member.builder()
                .name(name)
                .content(content)
                .build());

        // when
        List<Member> members = memberService.findAll();

        // then
        assertThat(members.size()).isEqualTo(1);
        assertThat(members.get(0).getName()).isEqualTo(name);
        assertThat(members.get(0).getContent()).isEqualTo(content);
    }

    @DisplayName("findById(): 회원을 조회한다")
    @Test
    void findById() {
        // given
        Member savedMember = memberRepository.save(Member.builder()
                .name("testName")
                .content("content")
                .build());

        // when
        Member member = memberService.findById(savedMember.getId());

        // then
        assertThat(member.getName()).isEqualTo("testName");
        assertThat(member.getContent()).isEqualTo("content");
    }

    @DisplayName("findById(): 존재하지 않는 회원은 예외가 발생한다")
    @Test
    void findById_fail() {
        // given
        Long id = 1L;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            memberService.findById(id);
        });
    }
}