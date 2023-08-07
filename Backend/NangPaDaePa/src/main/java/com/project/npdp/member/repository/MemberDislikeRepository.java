package com.project.npdp.member.repository;

import com.project.npdp.member.entity.Member;
import com.project.npdp.member.entity.MemberDislikeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDislikeRepository extends JpaRepository<MemberDislikeIngredient, Long> {
    void deleteAllByMember(Member member);
}
