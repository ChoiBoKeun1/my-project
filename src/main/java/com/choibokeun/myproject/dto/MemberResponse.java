package com.choibokeun.myproject.dto;

import com.choibokeun.myproject.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponse {
    private String name;
    private String content;

    public MemberResponse(Member member) {
        this.name = member.getName();
        this.content = member.getContent();
    }
}