package com.project.npdp.food.repository;

import com.project.npdp.food.entity.Ingredient;
import com.project.npdp.food.entity.Seasoning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasoningRepository extends JpaRepository<Seasoning, Long> {
    List<Ingredient> findByNameContainingIgnoreCase(String name);
}
