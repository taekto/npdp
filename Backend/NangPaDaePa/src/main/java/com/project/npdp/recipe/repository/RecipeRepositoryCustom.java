package com.project.npdp.recipe.repository;

import com.project.npdp.recipe.dto.request.FindAllRecipeWithConditionRequestDto;
import com.project.npdp.recipe.dto.response.RecipeDetailResponseDto;
import com.project.npdp.recipe.dto.response.RecipeResponseDto;
import com.project.npdp.recipe.entity.Recipe;

import java.util.List;

public interface RecipeRepositoryCustom {
    public List<Recipe> findRecipeByContent(String content);

    public RecipeDetailResponseDto findRecipeDetail(Long recipeId);

    public List<RecipeResponseDto> findAllRecipeWithCategory(FindAllRecipeWithConditionRequestDto findAllRecipeWithConditionRequestDto);

}
