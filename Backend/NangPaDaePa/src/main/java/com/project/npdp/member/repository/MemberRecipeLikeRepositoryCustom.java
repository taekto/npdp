package com.project.npdp.member.repository;

import com.project.npdp.recipe.dto.response.RecipeResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;


public interface MemberRecipeLikeRepositoryCustom {

    public List<RecipeResponseDto> findMemberRecipeHeartById(Long memberId);

}
