package com.project.npdp.food.repository;

import com.project.npdp.food.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    // refregirator에서 쓰는 것
    List<Ingredient> findByKorContaining(String name);

}
