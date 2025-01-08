package com.choibokeun.myproject.repository;

import com.choibokeun.myproject.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
