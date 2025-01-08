package com.choibokeun.myproject.dto;

import com.choibokeun.myproject.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddMemberRequest {
    private String name;
    private String content;

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .content(content)
                .build();
    }
}