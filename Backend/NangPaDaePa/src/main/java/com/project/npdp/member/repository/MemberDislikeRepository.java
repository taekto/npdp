package com.project.npdp.member.repository;

import com.project.npdp.member.entity.Member;
import com.project.npdp.member.entity.MemberDislikeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MemberDislikeRepository extends JpaRepository<MemberDislikeIngredient, Long>, MemberDislikeRepositoryCustom {
    void deleteAllByMember(Member member);
}
