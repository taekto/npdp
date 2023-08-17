package com.project.npdp.member.repository;

import com.project.npdp.member.entity.Member;
import com.project.npdp.member.entity.MemberAllergy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAllergyRepository extends JpaRepository<MemberAllergy, Long>, MemberAllergyRepositoryCustom {

    void deleteAllByMember(Member member);

}
