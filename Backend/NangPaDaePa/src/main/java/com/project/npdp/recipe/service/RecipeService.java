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
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {

//    private final RecipeRepository recipeRepository;
//    // 전체 레시피 조회
//    @Transactional(readOnly = true)
//    public List<Recipe> findAllRecipe() {
//        List<Recipe> all = recipeRepository.findAll();
//        return all;
//    }
//
//    // 레시피id로 레시피 조회
//    @Transactional(readOnly = true)
//    public Recipe findRecipe(Long recipeId) {
//        Recipe byId = recipeRepository.findById(recipeId).orElseThrow(() -> new IllegalArgumentException("레시피Id 조회불가"));
//        return byId;
//    }
 
}
