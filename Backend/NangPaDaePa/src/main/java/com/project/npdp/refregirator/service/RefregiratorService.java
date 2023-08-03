package com.project.npdp.refregirator.service;

import com.project.npdp.food.entity.Ingredient;
import com.project.npdp.food.entity.Seasoning;
import com.project.npdp.food.repository.IngredientRepository;
import com.project.npdp.food.repository.SeasoningRepository;
import com.project.npdp.member.entity.Member;
import com.project.npdp.member.repository.MemberRepository;
import com.project.npdp.recipe.dto.response.RecipeResponseDto;
import com.project.npdp.recipe.entity.Recipe;
import com.project.npdp.refregirator.dto.request.*;
import com.project.npdp.refregirator.dto.response.IngredientFindResponseDto;
import com.project.npdp.refregirator.dto.response.MemberIngredientFindResponseDto;
import com.project.npdp.refregirator.dto.response.SeasoningFindResponseDto;
import com.project.npdp.refregirator.entity.MemberSeasoning;
import com.project.npdp.refregirator.entity.Refregirator;
import com.project.npdp.refregirator.repository.MemberSeasoningRepository;
import com.project.npdp.refregirator.repository.RefregiratorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RefregiratorService {

    private final RefregiratorRepository refregiratorRepository;
    private final MemberSeasoningRepository memberSeasoningRepository;
    private final IngredientRepository ingredientRepository;
    private final SeasoningRepository seasoningRepository;
    private final MemberRepository memberRepository;

    // 재료삭제
    @Transactional
    public void deleteRecipeById(Long memberId, RefregiratorModifyIngredientRequestDto refregiratorModifyIngredientRequestDto) {

    }

    // 양념삭제
    @Transactional
    public void deleteMemberSeasoningById(Long memberId) {

    }

    // 재료 텍스트 조회
    @Transactional(readOnly = true)
    public List<IngredientFindResponseDto> findIngredientByKor(IngredientFindRequestDto ingredientFindRequestDto) {
        List<Ingredient> all = ingredientRepository.findByKorContaining(ingredientFindRequestDto.getKeyword());
        List<IngredientFindResponseDto> result = all.stream().map(ingredient -> IngredientFindResponseDto.builder()
                        .ingredientId(ingredient.getId())
                        .kor(ingredient.getKor())
                        .build())
                .collect(Collectors.toList());
        return result;
    }
    // 양념 텍스트 조회

    @Transactional(readOnly = true)
    public List<SeasoningFindResponseDto> findSeasoningByKor(SeasoningFindRequestDto seasoningFindRequestDto) {
        List<Seasoning> all = seasoningRepository.findByKorContaining(seasoningFindRequestDto.getKeyword());
        List<SeasoningFindResponseDto> result = all.stream().map(seasoning -> SeasoningFindResponseDto.builder()
                        .seasoningId(seasoning.getId())
                        .kor(seasoning.getKor())
                        .build())
                .collect(Collectors.toList());
        return result;
    }

    // 회원 재료 입력
    public void memberSaveIngredient(Long memberId, MemberIngredientSaveRequestDto memberIngredientSaveRequestDto) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("Member not found with id: "));
        Ingredient ingredient = ingredientRepository.findById(memberIngredientSaveRequestDto.getIngredientId())
                .orElseThrow(() -> new NoSuchElementException("Ingredient not found with id: "));
        Refregirator result = memberIngredientSaveRequestDto.toEntity(memberIngredientSaveRequestDto, member, ingredient);
        refregiratorRepository.save(result);

    }

    public void memberSaveIngredient(Long memberId, MemberSeasoningSaveRequestDto memberSeasoningSaveRequestDto) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("Member not found with id: "));
        Seasoning seasoning = seasoningRepository.findById(memberSeasoningSaveRequestDto.getSeasoningId())
                .orElseThrow(() -> new NoSuchElementException("Ingredient not found with id: "));
        MemberSeasoning result = memberSeasoningSaveRequestDto.toEntity(memberSeasoningSaveRequestDto, member, seasoning);
        memberSeasoningRepository.save(result);

    }



}
