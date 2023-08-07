package com.project.npdp.member.repository;

import com.project.npdp.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 이메일로 회원 조희
    Member findByEmail(String email);

    Optional<Member> findById(Long id);

}
