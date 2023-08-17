package com.project.npdp.member.repository;


import com.project.npdp.member.dto.response.MemberLatestResponseDto;
import com.project.npdp.member.entity.MemberRecipeLatest;
import com.project.npdp.member.entity.MemberRecipeLike;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.project.npdp.member.entity.QMemberRecipeLatest.memberRecipeLatest;
import static com.project.npdp.member.entity.QMemberRecipeLike.memberRecipeLike;
import static com.project.npdp.recipe.entity.QRecipe.recipe;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MemberRecipeLatestRepositoryImpl implements MemberRecipeLatestRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MemberLatestResponseDto> findLatestWithRecipesByMemberId(Long memberId) {
        List<MemberRecipeLatest> memberRecipeLatests = queryFactory
                .selectFrom(memberRecipeLatest)
                .join(memberRecipeLatest.recipe, recipe).fetchJoin()
                .where(memberRecipeLatest.member.id.eq(memberId))
                .orderBy(memberRecipeLatest.date.desc()) // 내림차순 정렬
                .limit(20) // 최근 20개 항목 제한
                .fetch();

        return memberRecipeLatests.stream()
                .map(latest -> MemberLatestResponseDto.builder()
                        .recipeId(latest.getRecipe().getId())
                        .name(latest.getRecipe().getName())
                        .imgBig(latest.getRecipe().getImgBig())
                        .imgSmall(latest.getRecipe().getImgSmall())
                        .category(latest.getRecipe().getCategory())
                        .date(latest.getDate())
                        .build())
                .collect(Collectors.toList());
    }


}
