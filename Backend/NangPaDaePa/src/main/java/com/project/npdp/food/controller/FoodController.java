package com.project.npdp.food.controller;


import com.project.npdp.food.dto.request.IngredientRequestDto;
import com.project.npdp.food.dto.request.SeasoningRequestDto;
import com.project.npdp.food.dto.response.IngredientResponseDto;
import com.project.npdp.food.dto.response.SeasoningResponseDto;
import com.project.npdp.food.entity.Ingredient;
import com.project.npdp.food.entity.Seasoning;
import com.project.npdp.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/foods")
public class FoodController {
    private final FoodService foodService;


    // 재료 전체 조회
    @GetMapping("/ingredient") // 주소주소
    public ResponseEntity<?> findAllIngredient() {
        List<IngredientResponseDto> allIngredient = foodService.findAllIngredient();
        return ResponseEntity.ok().body(ResponseEntity.ok().body(allIngredient));
    }

    // 재료 이름으로 조회
    @GetMapping("/ingredient/{name}")
    public ResponseEntity<?> findIngredientByName(@PathVariable("name") String name) {
        List<IngredientResponseDto> foundIngredient = foodService.findIngredientByName(name);
        return ResponseEntity.ok().body(ResponseEntity.ok().body(foundIngredient));
    }

    // 양념 전체 조회
    @GetMapping("/seasoning")
    public ResponseEntity<?> findAllSeasoning() {
        List<SeasoningResponseDto> allSeasoning = foodService.findAllSeasoning();
        return ResponseEntity.ok().body(ResponseEntity.ok().body(allSeasoning));
    }

    // 양념 이름으로 조회
    @GetMapping("/ingredient/{name}")
    public ResponseEntity<?> findSeasoningByName(@PathVariable("name") String name) {
        List<IngredientResponseDto> foundSeasoning = foodService.findSeasoningByName(name);
        return ResponseEntity.ok().body(ResponseEntity.ok().body(foundSeasoning));
    }


}
