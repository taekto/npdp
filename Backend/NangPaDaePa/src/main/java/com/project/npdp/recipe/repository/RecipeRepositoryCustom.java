package com.project.npdp.recipe.repository;

import com.project.npdp.recipe.dto.request.FindAllRecipeWithConditionRequestDto;
import com.project.npdp.recipe.dto.request.RecipeDetailRequestDto;
import com.project.npdp.recipe.dto.request.RecipeRecommendRequestDto;
import com.project.npdp.recipe.dto.response.RecipeDetailResponseDto;
import com.project.npdp.recipe.dto.response.RecipeHeartResponseDto;
import com.project.npdp.recipe.dto.response.RecipeRecommendResponseDto;
import com.project.npdp.recipe.dto.response.RecipeResponseDto;
import com.project.npdp.recipe.entity.Recipe;

import java.util.List;

public interface RecipeRepositoryCustom {
    public List<Recipe> findRecipeByContent(String content);

    public RecipeDetailResponseDto findRecipeDetail(RecipeDetailRequestDto recipeDetailRequestDto);

    public List<RecipeResponseDto> findAllRecipeWithCategory(FindAllRecipeWithConditionRequestDto findAllRecipeWithConditionRequestDto);

    public List<RecipeRecommendResponseDto> findRecipesWithSimilarity(RecipeRecommendRequestDto recipeRecommendRequestDto);

    public List<RecipeHeartResponseDto> findTop20RecipesByRecipeIdCount();
}
