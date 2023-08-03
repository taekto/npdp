package com.project.npdp.food.controller;


import com.project.npdp.food.dto.response.IngredientResponseDto;
import com.project.npdp.food.dto.response.SeasoningResponseDto;
import com.project.npdp.food.entity.Seasoning;
import com.project.npdp.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/foods")
public class FoodController {

    private final FoodService foodService;


    // 재료 전체 조회
    @GetMapping("/ingredient")
    public ResponseEntity<?> findAllIngredient() {
        List<IngredientResponseDto> allIngredient = foodService.findAllIngredient();
        return ResponseEntity.ok().body(allIngredient);
    }

    // 재료 이름으로 조회
    @GetMapping("/ingredient/search")
    public ResponseEntity<?> findIngredientByName(@RequestParam String name) {

        List<IngredientResponseDto> foundIngredient = foodService.findIngredientByName(name);
        if (foundIngredient.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(foundIngredient);

    }

    // 양념 전체 조회
    @GetMapping("/seasoning")
    public ResponseEntity<?> findAllSeasoning() {
        List<SeasoningResponseDto> allSeasoning = foodService.findAllSeasoning();
        return ResponseEntity.ok().body(allSeasoning);
    }


    // 양념 이름으로 조회
    @GetMapping("/seasoning/search")
    public ResponseEntity<?> findSeasoningByName(@RequestParam String name) {
        List<SeasoningResponseDto> foundSeasoning = foodService.findSeasoningByName(name);
        if (foundSeasoning.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(foundSeasoning);
    }


}
