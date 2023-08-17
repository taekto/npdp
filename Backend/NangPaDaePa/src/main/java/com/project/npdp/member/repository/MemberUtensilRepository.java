package com.project.npdp.member.repository;

import com.project.npdp.member.entity.Member;
import com.project.npdp.member.entity.MemberUtensil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberUtensilRepository extends JpaRepository<MemberUtensil, Long>, MemberUtensilRepositoryCustom {

    void deleteAllByMember(Member member);

}
