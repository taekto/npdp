package com.project.npdp.member.repository;

import com.project.npdp.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 이메일로 회원 조희
    List<Member> findByEmail(String email);

}
