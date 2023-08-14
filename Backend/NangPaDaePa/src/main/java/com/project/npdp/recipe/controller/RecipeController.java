package com.project.npdp.recipe.controller;

import com.project.npdp.recipe.dto.request.FindAllRecipeWithConditionRequestDto;
import com.project.npdp.recipe.dto.request.RecipeRecommendRequestDto;
import com.project.npdp.recipe.dto.response.RecipeDetailResponseDto;
import com.project.npdp.recipe.dto.response.RecipeRecommendResponseDto;
import com.project.npdp.recipe.dto.response.RecipeResponseDto;
import com.project.npdp.recipe.dto.response.RecipeWantResponseDto;
import com.project.npdp.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/recipes")
@CrossOrigin(origins = "http://localhost:8081")
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;

    // id로 Recipe Detail 조회
    @GetMapping("{recipeId}")
    public ResponseEntity<?> findRecipeById(@PathVariable("recipeId") Long recipeId) {
        RecipeDetailResponseDto recipeDetail = recipeService.findRecipeDetail(recipeId);
        return ResponseEntity.ok().body(recipeDetail);
    }

    // Recipe 전체 조회
    @GetMapping
    public ResponseEntity<?> findAllRecipe() {
        List<RecipeResponseDto> allRecipe = recipeService.findAllRecipe();
        return ResponseEntity.ok().body(ResponseEntity.ok().body(allRecipe));
    }

    // 레시피 검색
    @GetMapping("/want")
    public ResponseEntity<?> findWantRecipe(@RequestParam String content) {
        List<RecipeWantResponseDto> recipeByContent = recipeService.findWantRecipe(content);
        return ResponseEntity.ok().body(recipeByContent);
    }


    // 레시피 검색 분류(전체) + 카테고리(전체,밥,국/찌개,반찬,일품,후식)
    @GetMapping("/category")
    public ResponseEntity<?> findAllRecipeWithCategory(@RequestBody FindAllRecipeWithConditionRequestDto findAllRecipeWithConditionRequestDto) {
        List<RecipeResponseDto> result = recipeService.findAllRecipeWithCategory(findAllRecipeWithConditionRequestDto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/similarity")
    public ResponseEntity<?> findRecipesWithSimilarity(@RequestBody RecipeRecommendRequestDto recipeRecommendRequestDto) {
        List<RecipeRecommendResponseDto> result = recipeService.findRecipesWithSimilarity(recipeRecommendRequestDto);
        return ResponseEntity.ok().body(result);
    }

}
