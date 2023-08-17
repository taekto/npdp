package com.project.npdp.member.repository;


import com.project.npdp.member.entity.MemberRecipeLatest;
import com.project.npdp.member.entity.MemberRecipeLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRecipeLatestRepository extends JpaRepository<MemberRecipeLatest, Long>, MemberRecipeLatestRepositoryCustom {

    Optional<MemberRecipeLatest> findMemberRecipeLatestByMemberIdAndRecipeId(Long memberId, Long RecipeId);

}
