package org.example.dearmyany.repository;

import org.example.dearmyany.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // email 중복 체크
    Optional<Member> findByEmail(String email);
}
