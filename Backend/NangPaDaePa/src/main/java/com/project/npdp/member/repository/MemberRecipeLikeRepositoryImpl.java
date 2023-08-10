package com.project.npdp.member.repository;

import com.project.npdp.member.entity.MemberRecipeLike;
import com.project.npdp.recipe.dto.response.RecipeResponseDto;
import com.project.npdp.recipe.entity.Recipe;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.project.npdp.member.entity.QMemberRecipeLike.memberRecipeLike;
import static com.project.npdp.recipe.entity.QRecipe.recipe;

@RequiredArgsConstructor
@Slf4j
public class MemberRecipeLikeRepositoryImpl implements MemberRecipeLikeRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<RecipeResponseDto> findMemberRecipeHeartById(Long memberId) {
        List<MemberRecipeLike> likedRecipes = queryFactory
                .selectFrom(memberRecipeLike)
                .innerJoin(memberRecipeLike.recipe, recipe).fetchJoin()
                .where(memberRecipeLike.member.id.eq(memberId))
                .fetch();

        // memberRecipeLike 객체에서 RecipeResponseDto 객체로 변환
        return likedRecipes.stream()
                .map(like -> {
                    Recipe likedRecipe = like.getRecipe();
                    return RecipeResponseDto.builder()
                            .recipeId(likedRecipe.getId())
                            .name(likedRecipe.getName())
                            .imgBig(likedRecipe.getImgBig())
                            .imgSmall(likedRecipe.getImgSmall())
                            .category(likedRecipe.getCategory())
                            .build();
                })
                .collect(Collectors.toList());
    }

}
