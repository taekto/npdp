package com.project.npdp.refregirator.service;

import com.project.npdp.recipe.dto.response.RecipeResponseDto;
import com.project.npdp.recipe.entity.Recipe;
import com.project.npdp.refregirator.repository.MemberSeasoningRepository;
import com.project.npdp.refregirator.repository.RefregiratorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefregiratorService {

    private final RefregiratorRepository refregiratorRepository;
    private final MemberSeasoningRepository memberSeasoningRepository;

    // 재료삭제
    @Transactional
    public void deleteRecipeById(Long recipeId) {

        refregiratorRepository.deleteById(recipeId);
    }

    // 양념삭제
    @Transactional
    public void deleteMemberSeasoningById(Long memberSeasoningId) {
        memberSeasoningRepository.deleteById(memberSeasoningId);
    }

    // 재료 텍스트 입력

    // 양념 텍스트 입력

    // 재료 음성 입력

    // 양념 음성 입력

    // 회원 재료 조회

    // 회원 양념 조회
}
