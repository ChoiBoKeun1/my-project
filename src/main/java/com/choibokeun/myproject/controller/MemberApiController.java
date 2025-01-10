package com.choibokeun.myproject.controller;

import com.choibokeun.myproject.domain.Member;
import com.choibokeun.myproject.dto.AddMemberRequest;
import com.choibokeun.myproject.dto.MemberResponse;
import com.choibokeun.myproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/api/members")
    public ResponseEntity<Member> addMember(@RequestBody AddMemberRequest request) {
        Member savedMember = memberService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedMember);
    }

    @GetMapping("/api/members")
    public ResponseEntity<List<MemberResponse>> findAllMembers() {
        List<MemberResponse> members = memberService.findAll()
                .stream()
                .map(MemberResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(members);
    }

    @GetMapping("/api/members/{id}")
    public ResponseEntity<MemberResponse> findMember(@PathVariable("id") Long id) {
        Member member = memberService.findById(id);

        return ResponseEntity.ok()
                .body(new MemberResponse(member));
    }

    @DeleteMapping("/api/members/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable("id") Long id) {
        memberService.delete(id);

        return ResponseEntity.ok()
                .build();
    }
}
