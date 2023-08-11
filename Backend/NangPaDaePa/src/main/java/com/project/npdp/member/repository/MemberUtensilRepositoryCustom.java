package com.project.npdp.member.repository;

import com.project.npdp.member.entity.MemberUtensil;

import java.util.List;

public interface MemberUtensilRepositoryCustom {

    public List<MemberUtensil> selectMemberUtensil (Long memberId);

    public void deleteMemberUtensil(Long memberId);
}
