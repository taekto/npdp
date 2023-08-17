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
import com.project.npdp.refregirator.dto.response.MemberSeasoningFindResponseDto;
import com.project.npdp.refregirator.dto.response.SeasoningFindResponseDto;
import com.project.npdp.refregirator.entity.MemberSeasoning;
import com.project.npdp.refregirator.entity.Refregirator;
import com.project.npdp.refregirator.repository.MemberSeasoningRepository;
import com.project.npdp.refregirator.repository.RefregiratorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public void memberSaveIngredient(Long memberId, List<MemberIngredientSaveRequestDto> memberIngredientSaveRequestDto) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("Member not found with id: "));
        List<Refregirator> refregirators = new ArrayList<>();
        for (MemberIngredientSaveRequestDto dto : memberIngredientSaveRequestDto) {
            Ingredient ingredient = ingredientRepository.findById(dto.getIngredientId())
                    .orElseThrow(() -> new NoSuchElementException("Ingredient not found with id: "));
            Refregirator result = dto.toEntity(dto, member, ingredient);
            refregirators.add(result);
        }

        refregiratorRepository.saveAll(refregirators);

    }
    // 회원 양념 입력
    public void memberSaveSeasoning(Long memberId, List<MemberSeasoningSaveRequestDto> memberSeasoningSaveRequestDtos) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("Member not found with id: "));

        List<MemberSeasoning> memberSeasonings = new ArrayList<>();
        for (MemberSeasoningSaveRequestDto dto : memberSeasoningSaveRequestDtos) {
            Seasoning seasoning = seasoningRepository.findById(dto.getSeasoningId())
                    .orElseThrow(() -> new NoSuchElementException("Seasoning not found with id: "));
            MemberSeasoning result = dto.toEntity(dto, member, seasoning);
            memberSeasonings.add(result);
        }

        memberSeasoningRepository.saveAll(memberSeasonings);
    }

    // 회원 재료 조회
    public List<MemberIngredientFindResponseDto> findMemberIngredient(Long memberId) {
        List<MemberIngredientFindResponseDto> memberRefregirator = refregiratorRepository.findMemberIngredient(memberId);
        return memberRefregirator;
    }
    
    // 회원 양념 조회
    public List<MemberSeasoningFindResponseDto> findMemberSeasoning(Long memberId) {
        List<MemberSeasoningFindResponseDto> memberSeasoning = memberSeasoningRepository.findMemberSeasoning(memberId);
        return memberSeasoning;
    }

    // 회원 재료 수정 및 삭제
    public void modifyMemberIngredient(List<MemberIngredientModifyRequestDto> memberIngredientModifyRequestDto) {
        for (MemberIngredientModifyRequestDto ingredientModifyRequestDto : memberIngredientModifyRequestDto) {
            if (ingredientModifyRequestDto.isIsdelete()) {
                refregiratorRepository.deleteById(ingredientModifyRequestDto.getRefregiratorId());
            } else {
                Long refregiratorId = ingredientModifyRequestDto.getRefregiratorId();
                if (refregiratorRepository.existsById(refregiratorId)) {
                    Refregirator refregirator = refregiratorRepository.findById(refregiratorId).orElseThrow();
                    refregirator.updateValues(ingredientModifyRequestDto);
                } else {
                }
            }
        }
    }


    // 회원 양념 수정 및 삭제
    public void modifyMemberSeasoning(List<MemberSeasoningModifyRequestDto> memberSeasoningModifyRequestDtos) {
        for (MemberSeasoningModifyRequestDto memberSeasoningModifyRequestDto : memberSeasoningModifyRequestDtos) {
            if (memberSeasoningModifyRequestDto.isIsdelete()) {
                memberSeasoningRepository.deleteById(memberSeasoningModifyRequestDto.getMemberSeasoningId());
            } else {
                Long seasoningId = memberSeasoningModifyRequestDto.getMemberSeasoningId();
                if (memberSeasoningRepository.existsById(seasoningId)) {
                    MemberSeasoning memberSeasoning = memberSeasoningRepository.findById(seasoningId).orElseThrow();
                    memberSeasoning.updateValues(memberSeasoningModifyRequestDto);
                } else {
                }
            }
        }
    }

}
