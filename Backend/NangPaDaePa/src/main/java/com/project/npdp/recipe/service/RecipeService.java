package com.project.npdp.recipe.service;

import com.project.npdp.recipe.dto.request.RecipeRequestDto;
import com.project.npdp.recipe.dto.response.RecipeResponseDto;
import com.project.npdp.recipe.entity.Recipe;
import com.project.npdp.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    // 전체 레시피 조회
    @Transactional(readOnly = true)
    public List<Recipe> findAllRecipe() {
        List<Recipe> all = recipeRepository.findAll();
        return all;
    }

    // 레시피id로 레시피 조회
    @Transactional(readOnly = true)
    public Optional<Recipe> findRecipeById(Long recipeId) {
        Optional<Recipe> byId = recipeRepository.findById(recipeId);
        return byId;
    }

}