package com.project.npdp.member.repository;

import com.project.npdp.member.entity.MemberRecipeLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRecipeLikeRepository extends JpaRepository<MemberRecipeLike, Long>, MemberRecipeLikeRepositoryCustom {

    Optional<MemberRecipeLike> findMemberRecipeLikeByMemberIdAndRecipeId(Long memberId, Long RecipeId);

}
