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

    private final RecipeRepository recipeRepository;
    // 전체 레시피 조회
    @Transactional(readOnly = true)
    public List<RecipeResponseDto> findAllRecipe() {
        List<Recipe> all = recipeRepository.findAll();
        List<RecipeResponseDto> result = all.stream().map(r -> RecipeResponseDto.builder()
                        .id(r.getId())
                        .name(r.getName())
                        .imgBig(r.getImgBig())
                        .imgSmall(r.getImgSmall())
                        .category(r.getCategory())
                        .likes(r.getLikes())
                        .build())
                .collect(Collectors.toList());
        return result;
    }

    // 레시피id로 레시피 조회
    @Transactional(readOnly = true)
    public RecipeResponseDto findRecipeById(Long recipeId) {
        Optional<Recipe> byId = recipeRepository.findById(recipeId);
        return byId.map(r -> RecipeResponseDto.builder()
                        .id(r.getId())
                        .name(r.getName())
                        .imgBig(r.getImgBig())
                        .imgSmall(r.getImgSmall())
                        .category(r.getCategory())
                        .likes(r.getLikes())
                        .build())
                .orElseThrow(() -> new NoSuchElementException("레시피가 없습니다!"));
    }

}
