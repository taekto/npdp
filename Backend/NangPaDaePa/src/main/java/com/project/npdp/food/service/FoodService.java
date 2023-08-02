package com.project.npdp.food.service;

import com.project.npdp.food.dto.response.IngredientResponseDto;
import com.project.npdp.food.dto.response.SeasoningResponseDto;
import com.project.npdp.food.entity.Ingredient;
import com.project.npdp.food.entity.Seasoning;
import com.project.npdp.food.repository.IngredientRepository;
import com.project.npdp.food.repository.SeasoningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final IngredientRepository ingredientRepository;
    private final SeasoningRepository seasoningRepository;

    // 전체 재료 조회
    @Transactional(readOnly = true)
    public List<IngredientResponseDto> findAllIngredient() {
        List<Ingredient> all = ingredientRepository.findAll();
        List<IngredientResponseDto> result = all.stream().map(r->IngredientResponseDto.builder()
                .id(r.getId())
                .name(r.getName())
                .build())
                .collect(Collectors.toList());
        return result;
    }

    // 재료 이름으로 조회
    @Transactional(readOnly = true)
    public List<IngredientResponseDto> findIngredientByName(String name) {
        List<Ingredient> foundIngredient = ingredientRepository.findByNameContainingIgnoreCase(name);
        List<IngredientResponseDto> result = foundIngredient.stream().map(r->IngredientResponseDto.builder()
                        .id(r.getId())
                        .name(r.getName())
                        .build())
                .collect(Collectors.toList());
        return result;
    }

    // 전체 양념 조회
    @Transactional(readOnly = true)
    public List<SeasoningResponseDto> findAllSeasoning() {
        List<Seasoning> all = seasoningRepository.findAll();
        List<SeasoningResponseDto> result = all.stream().map(r-> SeasoningResponseDto.builder()
                .id(r.getId())
                .name(r.getName())
                .build())
                .collect(Collectors.toList());
        return result;
    }

    // 양념 이름으로 조회
    @Transactional(readOnly = true)
    public List<IngredientResponseDto> findSeasoningByName(String name) {
        List<Ingredient> foundSeasoning = seasoningRepository.findByNameContainingIgnoreCase(name);
        List<IngredientResponseDto> result = foundSeasoning.stream().map(r->IngredientResponseDto.builder()
                        .id(r.getId())
                        .name(r.getName())
                        .build())
                .collect(Collectors.toList());
        return result;
    }



}
