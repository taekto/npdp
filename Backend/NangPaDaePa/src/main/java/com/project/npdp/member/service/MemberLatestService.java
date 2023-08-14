package com.project.npdp.member.service;

import com.project.npdp.member.dto.request.MemberLatestRequestDto;
import com.project.npdp.member.dto.response.MemberLatestResponseDto;
import com.project.npdp.member.entity.Member;
import com.project.npdp.member.entity.MemberRecipeLatest;
import com.project.npdp.member.entity.MemberRecipeLike;
import com.project.npdp.member.repository.MemberRecipeLatestRepository;
import com.project.npdp.member.repository.MemberRepository;
import com.project.npdp.recipe.entity.Recipe;
import com.project.npdp.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberLatestService {

    private final MemberRecipeLatestRepository memberRecipeLatestRepository;
    private final MemberRepository memberRepository;
    private final RecipeRepository recipeRepository;

    // 회원 최근본 레시피 저장
    public void memberRecipeLatestInsert(MemberLatestRequestDto memberLatestRequestDto) {
        Member member = memberRepository.findById(memberLatestRequestDto.getMemberId()).orElseThrow();
        Recipe recipe = recipeRepository.findById(memberLatestRequestDto.getRecipeId()).orElseThrow();

        Optional<MemberRecipeLatest> memberRecipeLatestByMemberIdAndRecipeId = memberRecipeLatestRepository.findMemberRecipeLatestByMemberIdAndRecipeId(
                memberLatestRequestDto.getMemberId(), memberLatestRequestDto.getRecipeId()
        );

        // 만약 MemberRecipeLike 관계가 존재하지 않는다면 새로 저장
        if (!memberRecipeLatestByMemberIdAndRecipeId.isPresent()) {
            MemberRecipeLatest result = MemberRecipeLatest.builder().member(member)
                    .recipe(recipe).build();
            memberRecipeLatestRepository.save(result);
        }
        // 존재한다면?
        else {
            MemberRecipeLatest memberRecipeLatest = memberRecipeLatestByMemberIdAndRecipeId.get();
            memberRecipeLatest.updateDate();
        }
    }

    // 회원 최근본 레시피 조회
    public List<MemberLatestResponseDto> findMemberRecipeLatest(Long memberId) {
        List<MemberLatestResponseDto> result = memberRecipeLatestRepository.findLatestWithRecipesByMemberId(memberId);
        return result;
    }

}
