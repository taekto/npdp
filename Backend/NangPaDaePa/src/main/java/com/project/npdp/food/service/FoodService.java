package com.project.npdp.food.service;

import com.project.npdp.food.entity.Ingredient;
import com.project.npdp.food.entity.Seasoning;
import com.project.npdp.food.repository.IngredientRepository;
import com.project.npdp.food.repository.SeasoningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final IngredientRepository ingredientRepository;
    private final SeasoningRepository seasoningRepository;

    // 전체 재료 조회
    @Transactional(readOnly = true)
    public List<Ingredient> findAllIngredient() {
        List<Ingredient> all = ingredientRepository.findAll();
        return all;
    }

    // 재료id로 재료 조회
    @Transactional(readOnly = true)
    public Optional<Ingredient> findIngredientById(Long IngredientId) {
        Optional<Ingredient> byId = ingredientRepository.findById(IngredientId);
        return byId;
    }

    // 전체 양념 조회
    @Transactional(readOnly = true)
    public List<Seasoning> findAllSeasoning() {
        List<Seasoning> all = seasoningRepository.findAll();
        return all;
    }

    // 양념id로 양념 조회
    @Transactional(readOnly = true)
    public Optional<Seasoning> findSeasoningById(Long SeasoningId) {
        Optional<Seasoning> byId = seasoningRepository.findById(SeasoningId);
        return byId;
    }



}
