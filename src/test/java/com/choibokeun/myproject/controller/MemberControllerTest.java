package com.choibokeun.myproject.controller;

import com.choibokeun.myproject.domain.Member;
import com.choibokeun.myproject.dto.AddMemberRequest;
import com.choibokeun.myproject.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberApiControllerTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected WebApplicationContext context;
    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        memberRepository.deleteAll();
    }

    @DisplayName("addMember(): member 추가에 성공한다")
    @Test
    public void addMember() throws Exception {
        // given
        final String url = "/api/members";
        final String name = "testName";
        final String content = "content";
        final AddMemberRequest request =new AddMemberRequest(name, content);

        // when
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result.andExpect(status().isCreated());

        List<Member> members = memberRepository.findAll();

        assertThat(members.size()).isEqualTo(1);
        assertThat(members.get(0).getName()).isEqualTo(name);
        assertThat(members.get(0).getContent()).isEqualTo(content);
    }

    @DisplayName("findAllMembers(): member 목록 조회에 성공한다")
    @Test
    public void findAllMembers() throws Exception {
        // given
        final String url = "/api/members";
        Member savedMember = createDefaultMember();

        // when
        ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()))
                .andExpect(jsonPath("$[0].content").value(savedMember.getContent()));
    }

    @DisplayName("findMember(): member 조회에 성공한다")
    @Test
    public void findMember() throws Exception {
        // given
        final String url = "/api/members/{id}";
        Member savedMember = createDefaultMember();

        // when
        ResultActions result = mockMvc.perform((get(url, savedMember.getId())));

        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(savedMember.getName()))
                .andExpect(jsonPath("$.content").value(savedMember.getContent()));
    }

    private Member createDefaultMember() {
        return memberRepository.save(Member.builder()
                .name("testName")
                .content("content")
                .build()
        );
    }
}