package com.project.npdp.recipe.service;

import com.project.npdp.recipe.dto.response.RecipeIdResponseDto;
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
    @Transactional(readOnly = true)
    public RecipeIdResponseDto findRecipeById(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new IllegalArgumentException("레시피Id 조회불가"));

        RecipeIdResponseDto result = RecipeIdResponseDto.builder()
                .name(recipe.getName())
                .way(recipe.getWay())
                .weight(recipe.getWeight())
                .calorie(recipe.getCalorie())
                .carbohydrate(recipe.getCarbohydrate())
                .protein(recipe.getProtein())
                .fat(recipe.getFat())
                .salt(recipe.getSalt())
                .imgBig(recipe.getImgBig())
                .recipeSequenceList(recipe.getRecipeSequenceList())
                .build();
        return result;
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

}
