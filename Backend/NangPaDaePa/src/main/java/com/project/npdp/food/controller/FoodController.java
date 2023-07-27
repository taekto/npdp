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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    // 양념 전체 조회
    @GetMapping("/seasoning")
    public ResponseEntity<?> findAllSeasoning() {
        List<SeasoningResponseDto> allSeasoning = foodService.findAllSeasoning();
        return ResponseEntity.ok().body(ResponseEntity.ok().body(allSeasoning));
    }



}
