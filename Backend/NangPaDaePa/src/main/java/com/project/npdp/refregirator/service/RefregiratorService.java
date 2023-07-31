package com.project.npdp.refregirator.service;

import com.project.npdp.recipe.dto.response.RecipeResponseDto;
import com.project.npdp.recipe.entity.Recipe;
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

    // 전체 레시피 조회
//    @Transactional(readOnly = true)
//    public List<RecipeResponseDto> findAllRecipe() {
//
//        return ;
//    }

    // 레시피id로 레시피 조회
//    @Transactional(readOnly = true)
//    public RecipeResponseDto findRecipeById(Long recipeId) {
//
//        return ;
//    }
}
