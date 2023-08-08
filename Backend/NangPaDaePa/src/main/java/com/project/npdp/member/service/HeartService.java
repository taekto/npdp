package com.project.npdp.member.service;

import com.project.npdp.member.dto.request.MemberLikeRequestDto;
import com.project.npdp.member.entity.Member;
import com.project.npdp.member.entity.MemberRecipeLike;
import com.project.npdp.member.repository.MemberRecipeLikeRepository;
import com.project.npdp.member.repository.MemberRepository;
import com.project.npdp.recipe.entity.Recipe;
import com.project.npdp.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final MemberRecipeLikeRepository memberRecipeLikeRepository;
    private final MemberRepository memberRepository;
    private final RecipeRepository recipeRepository;

    // 좋아요 누르기
    public void MemberRecipeHeartInsert(MemberLikeRequestDto memberLikeRequestDto) {

        Member member = memberRepository.findById(memberLikeRequestDto.getMemberId()).orElseThrow();
        Recipe recipe = recipeRepository.findById(memberLikeRequestDto.getRecipeId()).orElseThrow();

        MemberRecipeLike result = MemberRecipeLike.builder().member(member)
                .recipe(recipe).build();
        memberRecipeLikeRepository.save(result);
    }
    
    // 좋아요 삭제
    public void MemberRecipeHeartDelete(MemberLikeRequestDto memberLikeRequestDto) {

        MemberRecipeLike result = memberRecipeLikeRepository.findMemberRecipeLikeByMemberIdAndRecipeId(memberLikeRequestDto.getMemberId(), memberLikeRequestDto.getRecipeId()).orElseThrow();
        
        memberRecipeLikeRepository.delete(result);
        
    }

    // 회원이 좋아요 한 레시피 조회
    

}
