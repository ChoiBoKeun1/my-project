package com.choibokeun.myproject.service;

import com.choibokeun.myproject.domain.Member;
import com.choibokeun.myproject.dto.AddMemberRequest;
import com.choibokeun.myproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public Member save(AddMemberRequest request) {
        return memberRepository.save(request.toEntity());
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public void delete(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
        memberRepository.delete(member);
    }
}
