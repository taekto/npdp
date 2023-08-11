package com.project.npdp.member.repository;

import com.project.npdp.member.entity.MemberDislikeIngredient;

import java.util.List;

public interface MemberDislikeRepositoryCustom {

    public List<MemberDislikeIngredient> selectDislikeIngredient(Long memberId);

    public void deleteDislikeIngredient(Long memberId);
}
