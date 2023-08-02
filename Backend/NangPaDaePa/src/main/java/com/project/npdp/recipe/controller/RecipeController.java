package com.project.npdp.recipe.controller;

import com.project.npdp.recipe.dto.response.RecipeResponseDto;
import com.project.npdp.recipe.dto.response.RecipeWantResponseDto;
import com.project.npdp.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    // id로 Recipe 조회
    @GetMapping("/{recipeId}")
    public ResponseEntity<?> findRecipeById(@PathVariable("recipeId") Long id) {
        RecipeResponseDto recipeById = recipeService.findRecipeById(id);
        return ResponseEntity.ok().body(ResponseEntity.ok().body(recipeById));
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


}
