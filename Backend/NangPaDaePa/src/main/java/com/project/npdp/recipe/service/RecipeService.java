package com.project.npdp.recipe.service;

import com.project.npdp.recipe.dto.request.FindAllRecipeWithConditionRequestDto;
import com.project.npdp.recipe.dto.response.RecipeDetailResponseDto;
import com.project.npdp.recipe.dto.response.RecipeResponseDto;
import com.project.npdp.recipe.dto.response.RecipeWantResponseDto;
import com.project.npdp.recipe.entity.Recipe;
import com.project.npdp.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RecipeService {

    private final RecipeRepository recipeRepository;

    // 전체 레시피 조회
    @Transactional(readOnly = true)
    public List<RecipeResponseDto> findAllRecipe() {
        List<Recipe> allRecipes = recipeRepository.findAll();
        List<RecipeResponseDto> result = allRecipes.stream()
                .map(recipe -> RecipeResponseDto.builder()
                        .recipeId(recipe.getId())
                        .name(recipe.getName())
                        .imgBig(recipe.getImgBig())
                        .imgSmall(recipe.getImgSmall())
                        .category(recipe.getCategory())
                        .build())
                .collect(Collectors.toList());
        return result;
    }

    // 레시피id로 레시피 조회
    public RecipeDetailResponseDto findRecipeDetail(Long recipeId) {
        RecipeDetailResponseDto recipeDetail = recipeRepository.findRecipeDetail(recipeId);
        return recipeDetail;
    }

    // 특정 레시피 검색
    @Transactional(readOnly = true)
    public List<RecipeWantResponseDto> findWantRecipe(String content) {
        List<Recipe> wantRecipe = recipeRepository.findRecipeByContent(content);
        List<RecipeWantResponseDto> result = wantRecipe.stream()
                .map(recipe -> RecipeWantResponseDto.builder()
                        .recipeId(recipe.getId())
                        .name(recipe.getName())
                        .imgBig(recipe.getImgBig())
                        .build())
                .collect(Collectors.toList());

        return result;
    }

    // 레시피 검색 분류(전체) + 카테고리(전체,밥,국/찌개,반찬,일품,후식)
    @Transactional(readOnly = true)
    public List<RecipeResponseDto> findAllRecipeWithCategory(FindAllRecipeWithConditionRequestDto findAllRecipeWithConditionRequestDto) {
        List<RecipeResponseDto> result = recipeRepository.findAllRecipeWithCategory(findAllRecipeWithConditionRequestDto);
        return result;
    }

}
