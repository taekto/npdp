package com.project.npdp.recipe.controller;

import com.project.npdp.recipe.dto.response.RecipeResponseDto;
import com.project.npdp.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private ResponseEntity<?> findAllRecipe() {
        List<RecipeResponseDto> allRecipe = recipeService.findAllRecipe();
        return ResponseEntity.ok().body(ResponseEntity.ok().body(allRecipe));
    }

}
